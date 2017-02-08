package Exercise4.warehouse;

import Exercise4.*;
import Exercise4.manufacturer.ManufacturerService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.rmi.CORBA.Util;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by pyoung on 2017-02-07.
 */
@WebService(endpointInterface = "Exercise4.warehouse.WarehouseService")
public class WarehouseServiceImpl implements WarehouseService {
    private static final String INVENTORY_FILE = "inventory.xml";

    @Override
    @WebMethod(operationName = "shipGoods")
    public Items shipGoods(Products orderedItems) {
        Products inventory = Utility.loadProducts(INVENTORY_FILE);
        Items customerList = new Items();

        for (Product p : orderedItems.getProducts()) {
            Product foundProduct = inventory.findProductByTypeBrand(p.getProductType(), p.getManufacturerName());

            if (foundProduct != null) {
                int quantity = foundProduct.getQuantity();
                if (quantity > 0) {
                    foundProduct.setQuantity(quantity - 1);
                    inventory.updateProduct(foundProduct);
                    customerList.addShippedItem(p);
                }
                else
                {
                    customerList.addNotShippedItem(p);
                }
            }
        }

        replenish(inventory);
        return customerList;
    }

    private void replenish(Products inventory) {
        for(Product p:inventory.getProducts()) {
            int amountToOrder = inventory.productBelowThreshold(p);
            if(amountToOrder != 0){
                orderFromManufacturer(amountToOrder, p);
            }
        }
    }

    private void orderFromManufacturer(int quantity, Product product){

        try {
            URL url = new URL("http://localhost:9000/ManufacturerService?wsdl");

            //1st argument service URI, refer to wsdl document above
            //2nd argument is service name, refer to wsdl document above
            QName qname = new QName("http://manufacturer.Exercise4/", "ManufacturerServiceImplService");

            Service service = Service.create(url, qname);

            ManufacturerService manufacturerService = service.getPort(ManufacturerService.class);

            PurchaseOrder po = new PurchaseOrder();
            po.setQuantity(quantity);
            po.setCustomerRef("A8D3J2A");
            po.setOrderNumb(Utility.generateOrderNumber());
            po.setProduct(product);
            po.setUnitPrice(product.getUnitPrice());

            boolean order = manufacturerService.ProcessPurchaseOrder(po);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}