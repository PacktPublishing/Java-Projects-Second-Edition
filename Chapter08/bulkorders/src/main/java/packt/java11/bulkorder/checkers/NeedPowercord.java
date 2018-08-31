//START SNIPPET NeedPowercord
package packt.java11.bulkorder.checkers;

//SNIPPET SKIL TILL "//IMPORT"

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import packt.java11.bulkorder.ConsistencyChecker;
import packt.java11.bulkorder.dtos.Order;

//IMPORT
@Component
@PoweredDevice
public class NeedPowercord implements ConsistencyChecker {
    private static final Logger log = LoggerFactory.getLogger(NeedPowercord.class);

    @Override
    public boolean isInconsistent(Order order) {
        log.info("checking order {}", order);
        var helper = new CheckHelper(order);
        return !helper.containsOneOf("126", "127", "128");
    }
}
//END SNIPPET