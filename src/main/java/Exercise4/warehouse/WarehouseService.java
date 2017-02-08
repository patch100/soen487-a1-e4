package Exercise4.warehouse;

import Exercise4.Items;
import Exercise4.Products;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by pyoung on 2017-02-07.
 */
//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WarehouseService {
    @WebMethod(operationName = "shipGoods")
    public Items shipGoods(Products orderedItems);
}