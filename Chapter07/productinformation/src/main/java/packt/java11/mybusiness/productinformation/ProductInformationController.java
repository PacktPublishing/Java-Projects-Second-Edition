// START SNIPPET ProductInformationController
package packt.java11.mybusiness.productinformation;
//SNIPPET SKIP TILL "//IMPORT"

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

//IMPORT
@RestController
public class ProductInformationController {
    private final ProductLookup lookup;

    public ProductInformationController(
            @Autowired ProductLookup lookup) {
        this.lookup = lookup;
    }

    @RequestMapping("/pi/{productId}")
    public ProductInformation getProductInformation(
            @PathVariable String productId) {
        return lookup.byId(productId);
    }

    @RequestMapping("/query/{query}")
    public List<String> lookupProductByTitle(
            @PathVariable String query,
            HttpServletRequest request) {
        return lookup.byQuery(query)
                .stream().map(s -> "/pi/" + s)
                .collect(Collectors.toList());
    }
}
// END SNIPPET