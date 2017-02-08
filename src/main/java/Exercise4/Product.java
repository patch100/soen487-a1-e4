package Exercise4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pyoung on 2017-02-07.
 */
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    private String manufacturerName;
    private String productType;
    private float unitPrice;
    private int quantity;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Product Type: " + this.getProductType() + " Manufacturer: " + this.getManufacturerName() + " Price: " + this.getUnitPrice();
    }
}
