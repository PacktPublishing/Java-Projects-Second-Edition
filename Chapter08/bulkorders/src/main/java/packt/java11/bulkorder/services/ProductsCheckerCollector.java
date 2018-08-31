package packt.java11.bulkorder.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import packt.java11.bulkorder.dtos.Order;
import packt.java11.bulkorder.dtos.ProductInformation;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequestScope
// START SNIPPET ProductsCheckerCollector
public class ProductsCheckerCollector {
    private static final Logger log =
            LoggerFactory.getLogger(ProductsCheckerCollector.class);

    private final ProductInformationCollector pic;

    public ProductsCheckerCollector
            (@Autowired ProductInformationCollector pic) {
        this.pic = pic;
    }

    public Set<Class<? extends Annotation>> _getProductAnnotations(Order order) {
        var piMap = pic.collectProductInformation(order);
        final var annotations = new HashSet<Class<? extends Annotation>>();
        for (var item : order.getItems()) {
            final var pi = piMap.get(item);
            if (pi != null && pi.getCheck() != null) {
                for (final var check : pi.getCheck()) {
                    annotations.addAll(pi.getCheck());
                }
            }
        }
        return annotations;
    }
// END SNIPPET

    //START SNIPPET ProductsCheckerCollector_getProductAnnotations
    public Set<Class<? extends Annotation>> getProductAnnotations(Order order) {
        var piMap = pic.collectProductInformation(order);
        return order.getItems().stream()
                .map(piMap::get)
                .filter(Objects::nonNull)
                .peek(pi -> {
                    if (pi.getCheck() == null) {
                        log.info("Product {} has no annotation", pi.getId());
                    }
                })
                .filter(ProductInformation::hasCheck)
                .peek(pi -> log.info("Product {} is annotated with class {}", pi.getId(), pi.getCheck()))
                .flatMap(pi -> pi.getCheck().stream())
                .collect(Collectors.toSet());
    }
    //END SNIPPET
}
