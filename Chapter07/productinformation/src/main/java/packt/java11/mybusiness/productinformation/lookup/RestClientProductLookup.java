// START SNIPPET RestClientProductLookup_head

package packt.java11.mybusiness.productinformation.lookup;

//SNIPPET SKIP TILL "//IMPORT"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import packt.java11.mybusiness.productinformation.ProductInformation;
import packt.java11.mybusiness.productinformation.ProductInformationServiceUrlBuilder;
import packt.java11.mybusiness.productinformation.ProductLookup;

import java.util.HashMap;
import java.util.List;

//IMPORT
@Component
public class RestClientProductLookup implements ProductLookup {
    private static Logger log = LoggerFactory.getLogger(RestClientProductLookup.class);

    final private ProductInformationServiceUrlBuilder piSUBuilder;

    public RestClientProductLookup(ProductInformationServiceUrlBuilder piSUBuilder) {
        this.piSUBuilder = piSUBuilder;
    }
    // END SNIPPET

    // START SNIPPET RestClientProductLookup_byId
    @Override
    public ProductInformation byId(String id) {
        var uriParameters = new HashMap<String, String>();
        uriParameters.put("id", id);
        var rest = new RestTemplate();
        var amount =
            rest.getForObject(piSUBuilder.url("inventory"),
                InventoryItemAmount.class,
                uriParameters);
        log.info("amount {}.", amount);
        if (amount.getAmount() > 0) {
            log.info("There items from {}. We are offering", id);
            return rest.getForObject(piSUBuilder.url("pi"),
                ProductInformation.class,
                uriParameters);
        } else {
            log.info("There are no items from {}. Amount is {}", id, amount);
            return ProductInformation.emptyProductInformation;
        }
    }
    // END SNIPPET

    // START SNIPPET RestClientProductLookup_byQuery
    @Override
    public List<String> byQuery(String query) {
        var uriParameters = new HashMap<String, String>();
        uriParameters.put("query", query);
        var rest = new RestTemplate();
        return rest.getForObject(piSUBuilder.url("query"), List.class, uriParameters);
    }
    // END SNIPPET
}
