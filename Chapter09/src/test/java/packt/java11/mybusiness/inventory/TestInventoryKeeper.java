package packt.java11.mybusiness.inventory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import packt.java11.mybusiness.product.Order;
import packt.java11.mybusiness.product.OrderItem;
import packt.java11.mybusiness.product.Product;

import javax.annotation.processing.AbstractProcessor;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class TestInventoryKeeper {
    private static final Logger log = LoggerFactory.getLogger(TestInventoryKeeper.class);


    //START SNIPPET testInventoryRemoval_1
    @Test
    public void testInventoryRemoval() throws InterruptedException {
        Inventory inventory = new Inventory();
        try (SubmissionPublisher<Order> p = new SubmissionPublisher<>();) {
            //END SNIPPET
            //START SNIPPET testInventoryRemoval_2
            p.subscribe(new InventoryKeeper(inventory));
            //END SNIPPET
            //START SNIPPET testInventoryRemoval_3
            Product product = new Product();
            inventory.store(product, 20);
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setAmount(10);
            Order order = new Order();
            List<OrderItem> items = new LinkedList<>();
            items.add(item);
            order.setItems(items);
            //END SNIPPET
            //START SNIPPET testInventoryRemoval_4
            for (int i = 0; i < 10; i++)
                p.submit(order);
            log.info("All orders were submitted");
            //END SNIPPET
            //START SNIPPET testInventoryRemoval_5
            for (int j = 0; j < 10; j++) {
                log.info("Sleeping a bit...");
                Thread.sleep(50);
            }
        }//try( p )
        //END SNIPPET
        log.info("Publisher was closed");
    }
}
