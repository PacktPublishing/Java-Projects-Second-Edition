//START SNIPPET OrderItem
package packt.java11.bulkorder.dtos;

public class OrderItem {
    private double amount;
    private String unit;
    private String productId;

    // ... setters and getters ...
// SNIPPET SKIP AFTER "//SETTERS END"

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
//SETTERS END
}
//END SNIPPET
