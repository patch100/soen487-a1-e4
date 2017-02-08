package Exercise4.manufacturer;

import Exercise4.Product;
import Exercise4.Products;
import Exercise4.PurchaseOrder;
import Exercise4.PurchaseOrders;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
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
//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ManufacturerService {
    @WebMethod(operationName = "processPurchaseOrder")
    public boolean ProcessPurchaseOrder(PurchaseOrder purchaseOrder);

    @WebMethod(operationName = "getProductInformation")
    public Product getProductInformation(String productType);

    @WebMethod(operationName = "receivePayment")
    public boolean receivePayment(String orderNum, float totalPrice);
}