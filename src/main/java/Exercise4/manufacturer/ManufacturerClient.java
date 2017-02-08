package Exercise4.manufacturer;

import Exercise4.Product;
import Exercise4.PurchaseOrder;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by pyoung on 2017-02-08.
 */
public class ManufacturerClient {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:9000/ManufacturerService?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://manufacturer.Exercise4/", "ManufacturerServiceImplService");

        Service service = Service.create(url, qname);

        ManufacturerService manufacturerService = service.getPort(ManufacturerService.class);

        Product p1 = new Product();
        p1.setManufacturerName("Brand1");
        p1.setProductType("DVD player");
        p1.setUnitPrice(100);
        p1.setQuantity(50);

        // A PO that should be processed without producing
        PurchaseOrder po = new PurchaseOrder();
        po.setQuantity(10);
        po.setCustomerRef("A8D3J2A");
        po.setOrderNumb("123123123");
        po.setProduct(p1);
        po.setUnitPrice(200);

        // A PO that should be processed with producing and return true
        PurchaseOrder po2 = new PurchaseOrder();
        po2.setQuantity(100);
        po2.setCustomerRef("A8D3J2A");
        po2.setOrderNumb("123123124");
        po2.setProduct(p1);
        po2.setUnitPrice(200);

        // A PO that should be rejected due to low price
        PurchaseOrder po3 = new PurchaseOrder();
        po3.setQuantity(1);
        po3.setCustomerRef("A8D3J2A");
        po3.setOrderNumb("123123125");
        po3.setProduct(p1);
        po3.setUnitPrice(10);

        System.out.println(manufacturerService.ProcessPurchaseOrder(po));
        System.out.println(manufacturerService.ProcessPurchaseOrder(po2));
        System.out.println(manufacturerService.ProcessPurchaseOrder(po3));

        Product p = manufacturerService.getProductInformation(po.getProduct().getProductType());
        System.out.println(p.toString());
        System.out.println(manufacturerService.receivePayment(po.getOrderNumb(), po.getUnitPrice() * po.getQuantity()));
    }

}
