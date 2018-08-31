package packt.java11.mybusiness.product;

public class ProductIsOutOfStock extends Exception {
    public ProductIsOutOfStock(Product product) {
        super(product.toString());
    }
}
