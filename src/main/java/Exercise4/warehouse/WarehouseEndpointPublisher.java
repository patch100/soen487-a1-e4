package Exercise4.warehouse;

import Exercise4.manufacturer.ManufacturerServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by pyoung on 2017-02-08.
 */
public class WarehouseEndpointPublisher {
    public static void main(String[] argv) {
        Endpoint.publish("http://localhost:9009/WarehouseService", new WarehouseServiceImpl());
    }
}
