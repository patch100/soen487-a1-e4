package Exercise4.manufacturer;

import Exercise4.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.rmi.CORBA.Util;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Endpoint;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by pyoung on 2017-02-07.
 */
@WebService(endpointInterface = "Exercise4.manufacturer.ManufacturerService")
public class ManufacturerServiceImpl implements ManufacturerService {
    private static final String PRODUCTS_FILE = "products.xml";
    private static final String ORDERS_FILE = "orders.xml";

    @WebMethod(operationName = "processPurchaseOrder")
    public boolean ProcessPurchaseOrder(PurchaseOrder purchaseOrder) {
        Products products = Utility.loadProducts(PRODUCTS_FILE);

        Product product = products.findProductByTypeBrand(purchaseOrder.getProduct().getProductType(), purchaseOrder.getProduct().getManufacturerName());

        if (!checkPrice(product, purchaseOrder.getUnitPrice())) {
            return false;
        }

        int amountOrdered = purchaseOrder.getQuantity();
        int amountAvailable = product.getQuantity();

        if (amountOrdered > amountAvailable) {
            int remainder = Produce(amountOrdered - amountAvailable, product);
            while (remainder > 0){
                remainder = Produce(remainder, product);
            }
        } else {
            product.setQuantity(amountAvailable - amountOrdered);
        }

        products.updateProduct(product);

        Utility.saveProducts(PRODUCTS_FILE, products);

        purchaseOrder.setPaid(false);

        saveOrderToFile(purchaseOrder);

        return true;
    }

    @WebMethod(operationName = "getProductInformation")
    public Product getProductInformation(String productType) {
        Products products = Utility.loadProducts(PRODUCTS_FILE);
        Product product = products.findProductByTypeBrand(productType, "Brand1");

        return product == null ? null : product;
    }

    @WebMethod(operationName = "receivePayment")
    public boolean receivePayment(String orderNum, float totalPrice) {
        PurchaseOrders orders = getOrdersFromFile();
        PurchaseOrder order = orders.getOrderByOrderNumber(orderNum);

        if (order != null && totalPrice == (order.getUnitPrice() * order.getQuantity())) {

            order.setPaid(true);
            orders.updateOrder(order);
            saveOrdersToFile(orders);
            return true;
        }

        return false;
    }

    private boolean checkPrice(Product p, float offeredPrice) {
        return offeredPrice >= p.getUnitPrice();
    }

    private int Produce(int amount, Product p) {
        int remainder = 0;
        if (amount < 100) {
            int oldQuantity = p.getQuantity();
            oldQuantity += amount;
            p.setQuantity(oldQuantity);
        } else {
            p.setQuantity(100);
            remainder = amount - 100;
        }

        return remainder;
    }

    private void saveOrderToFile(PurchaseOrder purchaseOrder) {

        PurchaseOrders orders = getOrdersFromFile();

        if(orders == null) {
            orders = new PurchaseOrders();
        }
        orders.addOrder(purchaseOrder);
        saveOrdersToFile(orders);
    }

    private void saveOrdersToFile(PurchaseOrders orders) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(PurchaseOrders.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(orders, System.out);
            m.marshal(orders, new File(ORDERS_FILE));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private PurchaseOrders getOrdersFromFile() {

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(PurchaseOrders.class);
            Unmarshaller um = context.createUnmarshaller();

            PurchaseOrders orders = null;
            try {
                File f = new File(ORDERS_FILE);
                if(f.exists()) {
                    orders = (PurchaseOrders) um.unmarshal(new FileReader(ORDERS_FILE));
                }
                else
                {
                    return null;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return orders;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}