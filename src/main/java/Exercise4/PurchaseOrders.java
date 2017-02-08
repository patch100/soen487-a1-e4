package Exercise4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by pyoung on 2017-02-07.
 */
@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseOrders {
    @XmlElement(name = "order")
    private ArrayList<PurchaseOrder> orders = null;

    public ArrayList<PurchaseOrder> getProducts() {
        return orders;
    }

    public void setProducts(ArrayList<PurchaseOrder> orders) {
        this.orders = orders;
    }

    public void addOrder(PurchaseOrder purchaseOrder) {
        if(orders == null) {
            orders = new ArrayList<PurchaseOrder>();
        }
        orders.add(purchaseOrder);
    }

    public PurchaseOrder getOrderByOrderNumber(String orderNumber) {
        for (PurchaseOrder o : orders) {
            if (o.getOrderNumb().equals(orderNumber)) {
                return o;
            }
        }
        return null;
    }

    public void updateOrder(PurchaseOrder order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumb().equals(order.getOrderNumb())) {
                orders.set(i, order);
            }
        }
    }
}
