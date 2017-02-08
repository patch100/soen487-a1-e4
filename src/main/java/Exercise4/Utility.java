package Exercise4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pyoung on 2017-02-08.
 */
public class Utility {
    public static void saveProducts(String file, Products products) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Products.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(products, System.out);
            m.marshal(products, new File(file));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Products loadProducts(String file) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Products.class);
            Unmarshaller um = context.createUnmarshaller();

            Products products = null;
            try {
                products = (Products) um.unmarshal(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return products;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Initialize the products xml with some data
    public static void seed(String file) {
        Product p1 = new Product();
        p1.setManufacturerName("Brand1");
        p1.setProductType("DVD player");
        p1.setUnitPrice(100);
        p1.setQuantity(50);

        Product p2 = new Product();
        p2.setManufacturerName("Brand1");
        p2.setProductType("video camera");
        p2.setUnitPrice(325);
        p2.setQuantity(200);

        Product p3 = new Product();
        p3.setManufacturerName("Brand1");
        p3.setProductType("TV");
        p3.setUnitPrice(500);
        p3.setQuantity(25);

        Product p4 = new Product();
        p4.setManufacturerName("Brand2");
        p4.setProductType("DVD player");
        p4.setUnitPrice(100);
        p4.setQuantity(50);

        Product p5 = new Product();
        p5.setManufacturerName("Brand2");
        p5.setProductType("video camera");
        p5.setUnitPrice(325);
        p5.setQuantity(200);

        Product p6 = new Product();
        p6.setManufacturerName("Brand2");
        p6.setProductType("TV");
        p6.setUnitPrice(500);
        p6.setQuantity(25);

        Product p7 = new Product();
        p7.setManufacturerName("Brand3");
        p7.setProductType("DVD player");
        p7.setUnitPrice(100);
        p7.setQuantity(50);

        Product p8 = new Product();
        p8.setManufacturerName("Brand3");
        p8.setProductType("video camera");
        p8.setUnitPrice(325);
        p8.setQuantity(200);

        Product p9 = new Product();
        p9.setManufacturerName("Brand3");
        p9.setProductType("TV");
        p9.setUnitPrice(500);
        p9.setQuantity(25);

        Products products = new Products();
        ArrayList<Product> plist = new ArrayList<Product>();
        plist.add(p1);
        plist.add(p2);
        plist.add(p3);
        plist.add(p4);
        plist.add(p5);
        plist.add(p6);
        plist.add(p7);
        plist.add(p8);
        plist.add(p9);

        products.setProducts(plist);
        Utility.saveProducts(file, products);
    }

    private static SecureRandom random = new SecureRandom();

    public static String generateOrderNumber() {
        return new BigInteger(130, random).toString(9);
    }

    public static int generateNumberInRange(int range){
        Random r = new Random();
        return Math.abs(r.nextInt()) % range + 1;
    }
}
