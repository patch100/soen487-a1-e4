package Exercise4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pyoung on 2017-02-07.
 */
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {
    @XmlElement(name = "product")
    private ArrayList<Product> products = null;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Product findProductByTypeBrand(String productType, String brand) {
        for (Product p : products) {
            if (p.getProductType().equals(productType) && p.getManufacturerName().equals(brand)) {
                return p;
            }
        }
        return null;
    }

    public int productBelowThreshold(Product product) {
        switch (product.getProductType()) {
            case "DVD player":
                if(product.getQuantity() <= 20)
                    return 20 - product.getQuantity() + 10;
                break;
            case "video camera":
                if(product.getQuantity() <= 100)
                    return 100 - product.getQuantity() + 50;
                break;
            default:
                if(product.getQuantity() <= 5)
                    return 5 - product.getQuantity() + 10;
                break;
        }

        return 0;
    }

    public void updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductType().equals(updatedProduct.getProductType()) && products.get(i).getManufacturerName().equals(updatedProduct.getManufacturerName())) {
                products.set(i, updatedProduct);
            }
        }
    }
}
