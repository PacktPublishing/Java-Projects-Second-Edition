//START SNIPPET Checker
package packt.java11.bulkorder.services;


// SNIPPET SKIP TILL "//IMPORT"

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import packt.java11.bulkorder.ConsistencyChecker;
import packt.java11.bulkorder.dtos.Order;
import packt.java11.bulkorder.dtos.ProductInformation;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
//IMPORT

@Component()
@RequestScope
public class Checker {
    private static final Logger log = LoggerFactory.getLogger(Checker.class);

    private final Collection<ConsistencyChecker> checkers;
    private final ProductInformationCollector piCollector;
    private final ProductsCheckerCollector pcCollector;
    private final CheckerScriptExecutor executor;


    public Checker(@Autowired Collection<ConsistencyChecker> checkers,
                   @Autowired ProductInformationCollector piCollector,
                   @Autowired ProductsCheckerCollector pcCollector,
                   @Autowired CheckerScriptExecutor executor
    ) {
        this.checkers = checkers;
        this.piCollector = piCollector;
        this.pcCollector = pcCollector;
        this.executor = executor;
    }

    public boolean isConsistent(Order order) {
        final var map = piCollector.collectProductInformation(order);
        if (map == null) {
            return false;
        }
        final var annotations = pcCollector.getProductAnnotations(order);
        for (final var checker : checkers) {
            for (final var annotation : checker.getClass().getAnnotations()) {
                if (annotations.contains(annotation.annotationType())) {
                    if (checker.isInconsistent(order)) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
    // SNIPPET SKIP AFTER "END SNIPPET"

    //START SNIPPET __isConsistent
    public boolean __isConsistent(Order order) {
        var map = piCollector.collectProductInformation(order);
        if (map == null) {
            return false;
        }
        final var as = pcCollector.getProductAnnotations(order);
        return !checkers.stream().anyMatch(
                c -> Arrays.stream(c.getClass().getAnnotations()
                ).filter(a -> as.contains(a.annotationType())
                ).anyMatch(x -> c.isInconsistent(order)
                ));
    }
    // END SNIPPET
    // SNIPPET SKIP AFTER "END SNIPPET"

    /**
     * Check that an order is consistent calling all consistency checkers that are relevant for the order.
     *
     * @param order
     * @return true if the order is consistent by all checkers
     */
    //START SNIPPET _isConsistent
    public boolean _isConsistent(Order order) {
        final var map = piCollector.collectProductInformation(order);
        if (map == null) {
            return false;
        }
        final var annotations = pcCollector.getProductAnnotations(order);
        var needAnntn = (Predicate<Annotation>) an ->
                annotations.contains(an.annotationType());
        var consistent = (Predicate<ConsistencyChecker>) c ->
                Arrays.stream(c.getClass().getAnnotations())
                        .parallel()
                        .unordered()
                        .filter(needAnntn)
                        .anyMatch(x -> c.isInconsistent(order));
        final var checkersOK = !checkers.stream().anyMatch(consistent);
        final var scriptsOK = !map.values().parallelStream().
                map(ProductInformation::getCheckScript).
                filter(Objects::nonNull).
                anyMatch(s -> executor.notConsistent(s, order));
        return checkersOK && scriptsOK;
    }
//END SNIPPET
//START SNIPPET Checker
}
//END SNIPPET