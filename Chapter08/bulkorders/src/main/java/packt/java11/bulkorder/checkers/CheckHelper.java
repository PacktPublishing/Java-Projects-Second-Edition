package packt.java11.bulkorder.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import packt.java11.bulkorder.ConsistencyChecker;
import packt.java11.bulkorder.dtos.Order;
import packt.java11.bulkorder.dtos.OrderItem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static packt.java11.bulkorder.checkers.Tuple.tuple;

public class CheckHelper {
    private static final Logger log = LoggerFactory.getLogger(CheckHelper.class);
    final private Order order;

    public CheckHelper(Order order) {
        this.order = order;
    }


    //START SNIPPET _containsOneOf
    public boolean _containsOneOf(String... ids) {
        for (final var item : order.getItems()) {
            for (final var id : ids) {
                if (item.getProductId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }
    //END SNIPPET

    //START SNIPPET containsOneOf
    public boolean containsOneOf(String... ids) {
        return order.getItems().parallelStream()
            .map(OrderItem::getProductId)
            .flatMap(itemId -> Arrays.stream(ids)
                .map(id -> tuple(itemId, id)))
            .filter(t -> Objects.equals(t.s, t.r))
            .collect(Collectors.counting()) > 0;
    }
    //END SNIPPET

    //START SNIPPET isInconsistent
    private boolean isInconsistent(ConsistencyChecker checker, Order order) {
        final var methods = checker.getClass().getDeclaredMethods();
        if (methods.length != 1) {
            log.error("The checker {} has zero or more than one methods",
                checker.getClass());
            return false;
        }
        final var method = methods[0];
        final boolean inconsistent;
        try {
            inconsistent = (boolean) method.invoke(checker, order);
        } catch (InvocationTargetException |
            IllegalAccessException |
            ClassCastException e) {
            log.error("Calling the method {} on class {} threw exception",
                method, checker.getClass());
            log.error("The exception is ", e);
            return false;
        }
        return inconsistent;
    }
    //END SNIPPET

    //START SNIPPET getSingleDeclaredPublicMethod
    private Method getSingleDeclaredPublicMethod(
        ConsistencyChecker checker) {
        final var methods = checker.getClass().getDeclaredMethods();
        Method singleMethod = null;
        for (final var method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                if (singleMethod != null) {
                    return null;
                }
                singleMethod = method;
            }
        }
        return singleMethod;
    }
    //END SNIPPET
}
