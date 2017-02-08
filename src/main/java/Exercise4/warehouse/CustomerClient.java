package Exercise4.warehouse;

import Exercise4.*;
import Exercise4.manufacturer.ManufacturerService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by pyoung on 2017-02-08.
 */
public class CustomerClient {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:9009/WarehouseService?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://warehouse.Exercise4/", "WarehouseServiceImplService");

        Service service = Service.create(url, qname);

        WarehouseService warehouseService = service.getPort(WarehouseService.class);

        ArrayList<Product> itemList = new ArrayList<Product>();
        for(int i = 0; i < 300; i++) {
            Product p1 = new Product();
            String productType = "";
            switch (Utility.generateNumberInRange(3)) {
                case 1:
                    productType = "DVD Player";
                    break;
                case 2:
                    productType = "video camera";
                    break;
                default:
                    productType = "TV";
                    break;
            }
            p1.setManufacturerName("Brand" + Utility.generateNumberInRange(3));
            p1.setProductType(productType);
            p1.setUnitPrice(100);
            p1.setQuantity(50);
            itemList.add(p1);
        }

        Products products = new Products();
        products.setProducts(itemList);

        Items items = warehouseService.shipGoods(products);

        System.out.println("SHIPPED ITEMS");
        for(Product p: items.getShippedItems()) {
            System.out.println(p.toString());
        }

        System.out.println("\nNOT SHIPPED ITEMS");
        ArrayList<Product> itemsNotShipped = items.getNotShippedItems();
        if(itemsNotShipped != null){
            for(Product p:itemsNotShipped) {
                System.out.println(p.toString());
            }
        }

    }

}
