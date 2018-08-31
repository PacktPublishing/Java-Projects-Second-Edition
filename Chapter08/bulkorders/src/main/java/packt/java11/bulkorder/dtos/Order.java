//START SNIPPET Order
package packt.java11.bulkorder.dtos;

//SNIPPET SKIP TILL "//IMPORT"

import java.util.List;
//IMPORT

public class Order {
    private String orderId;
    private List<OrderItem> items;
    private String customerId;


    // ... setters and getters ...
// SNIPPET SKIP AFTER "//SETTERS END"
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
//SETTERS END
}
//END SNIPPET