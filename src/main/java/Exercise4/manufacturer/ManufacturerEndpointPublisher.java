package Exercise4.manufacturer;

import Exercise4.Utility;

import javax.xml.ws.Endpoint;

/**
 * Created by pyoung on 2017-02-08.
 */
public class ManufacturerEndpointPublisher {
    public static void main(String[] argv) {
        Endpoint.publish("http://localhost:9000/ManufacturerService", new ManufacturerServiceImpl());
    }
}
