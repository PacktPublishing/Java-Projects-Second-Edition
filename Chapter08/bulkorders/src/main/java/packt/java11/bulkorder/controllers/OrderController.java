//START SNIPPET OrderController
package packt.java11.bulkorder.controllers;

//SNIPPET SKIP TILL "//IMPORT"

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import packt.java11.bulkorder.dtos.Confirmation;
import packt.java11.bulkorder.dtos.Order;
import packt.java11.bulkorder.services.Checker;
//IMPORT

@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger((OrderController.class));
    private final Checker checker;

    public OrderController(@Autowired Checker checker) {
        this.checker = checker;
    }

    @RequestMapping("/order")
    public Confirmation getProductInformation(@RequestBody Order order) {
        if (checker.isConsistent(order)) {
            return Confirmation.accepted(order);
        } else {
            return Confirmation.refused(order);
        }
    }
}
//END SNIPPET