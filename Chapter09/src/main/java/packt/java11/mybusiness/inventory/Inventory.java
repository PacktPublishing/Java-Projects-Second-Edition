//START SNIPPET Inventory
package packt.java11.mybusiness.inventory;

// SNIPPET SKIP TILL "//IMPORT"

import org.springframework.stereotype.Component;
import packt.java11.mybusiness.product.Product;
import packt.java11.mybusiness.product.ProductIsOutOfStock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//IMPORT

@Component
public class Inventory {
    private final Map<Product, InventoryItem> inventory =
            new ConcurrentHashMap<>();

    private InventoryItem getItem(Product product) {
        inventory.putIfAbsent(product, new InventoryItem());
        return inventory.get(product);
    }

    public void store(Product product, long amount) {
        getItem(product).store(amount);
    }

    public void remove(Product product, long amount)
            throws ProductIsOutOfStock {
        if (getItem(product).remove(amount) != amount)
            throw new ProductIsOutOfStock(product);
    }
}
//END SNIPPET