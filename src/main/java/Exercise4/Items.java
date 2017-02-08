package Exercise4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by pyoung on 2017-02-07.
 */
@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class Items
{
    @XmlElement(name = "shippedItem")
    private ArrayList<Product> shippedItems = null;

    @XmlElement(name = "notShippedItem")
    private ArrayList<Product> notShippedItems = null;

    public ArrayList<Product> getShippedItems() {
        return shippedItems;
    }

    public void setShippedItems(ArrayList<Product> shippedItems) {
        this.shippedItems = shippedItems;
    }

    public ArrayList<Product> getNotShippedItems() {
        return notShippedItems;
    }

    public void setNotShippedItems(ArrayList<Product> notShippedItems) {
        this.notShippedItems = notShippedItems;
    }

    public void addShippedItem(Product p) {
        if(this.shippedItems == null) {
            this.shippedItems = new ArrayList<Product>();
        }
        shippedItems.add(p);
    }

    public void addNotShippedItem(Product p) {
        if(this.notShippedItems == null) {
            this.notShippedItems = new ArrayList<Product>();
        }
        notShippedItems.add(p);
    }
}
