package packt.java11.bulkorder.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import packt.java11.bulkorder.ConsistencyChecker;
import packt.java11.bulkorder.dtos.Order;

@Component
public class FirstChecker implements ConsistencyChecker {
    private static final Logger log = LoggerFactory.getLogger(FirstChecker.class);
    @Override
    public boolean isInconsistent(Order order) {
        log.info("FirstChecker checking order {}", order);
        return false;
    }
}
