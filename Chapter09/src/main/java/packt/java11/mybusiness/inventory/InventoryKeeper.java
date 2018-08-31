//START SNIPPET InventoryKeeper
package packt.java11.mybusiness.inventory;

// SNIPPET SKIP TILL "//IMPORT"

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import packt.java11.mybusiness.product.Order;
import packt.java11.mybusiness.product.OrderItem;
import packt.java11.mybusiness.product.ProductIsOutOfStock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

//IMPORT

public class InventoryKeeper implements Flow.Subscriber<Order> {
    private static final Logger log = LoggerFactory.getLogger(InventoryKeeper.class);
    private static final long WORKERS = 3;
    private final Inventory inventory;
    private Flow.Subscription subscription = null;
    private ExecutorService service = Executors.newFixedThreadPool((int) WORKERS);

    public InventoryKeeper(@Autowired Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        log.info("onSubscribe was called");
        subscription.request(3);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Order order) {
        service.submit(() -> {
                    log.info("Thread {}", Thread.currentThread().getName());
                    for (final var item : order.getItems()) {
                        try {
                            inventory.remove(item.getProduct(), item.getAmount());
                            log.info("{} items removed from stock", item.getAmount());
                        } catch (ProductIsOutOfStock exception) {
                            log.error("Product out of stock");
                        }
                    }
                    subscription.request(1);
                }
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError was called for {}", throwable);
    }

    @Override
    public void onComplete() {
        log.info("onComplete was called");
    }
}
//END SNIPPET