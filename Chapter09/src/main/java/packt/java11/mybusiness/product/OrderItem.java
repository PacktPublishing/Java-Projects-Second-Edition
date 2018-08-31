package packt.java11.mybusiness.product;

public class OrderItem {
    private long amount;
    private Product product;

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }
}
