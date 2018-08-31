//START SNIPPET ResourceBasedProductLookup

package packt.java11.bulkorder.services;
//SNIPPET SKIP TILL "//IMPORT"
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import packt.java11.bulkorder.ProductLookup;
import packt.java11.bulkorder.dtos.ProductInformation;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
//IMPORT

@Service
public class ResourceBasedProductLookup implements ProductLookup {
    private static final Logger log =
        LoggerFactory.getLogger(ResourceBasedProductLookup.class);

    private ProductInformation fromJSON(InputStream jsonStream) throws IOException {
        final var mapper = new ObjectMapper();
        return mapper.readValue(jsonStream, ProductInformation.class);
    }

//SNIPPET SKIP TILL "// ..."
    final private Map<String, ProductInformation> products = new HashMap<>();
    private boolean productsAreNotLoaded = true;
// ...
    private void loadProducts() {
        if (productsAreNotLoaded) {
            try {
                final var resources = new PathMatchingResourcePatternResolver().
                        getResources("classpath:products/*.json");
                for (final var resource : resources) {
                    loadResource(resource);
                }
                productsAreNotLoaded = false;
            } catch (IOException ex) {
                log.error("Test resources can not be read", ex);
            }
        }
    }

    private void loadResource(Resource resource) throws IOException {
        final var dotPos = resource.getFilename().lastIndexOf('.');
        final var id = resource.getFilename().substring(0, dotPos);
        final var pi = fromJSON(resource.getInputStream());
        pi.setId(id);
        products.put(id, pi);
        if( pi.getCheck() != null )
        log.info("Product {} check is {}",id,pi.getCheck().get(0));
    }
// ...
//END SNIPPET
    private static final List<String> noProducts = new LinkedList<>();

    @Override
    public ProductInformation byId(String id) {
        loadProducts();
        if (products.containsKey(id)) {
            return products.get(id);
        } else {
            log.error("There is no product for id {}", id);
            return ProductInformation.emptyProductInformation;
        }
    }

    @Override
    public List<String> byQuery(String query) {
        log.info("query is {}", query);
        loadProducts();
        List<String> pis = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(query, "&=");
        while (st.hasMoreTokens()) {
            final String key = st.nextToken();
            if (st.hasMoreTokens()) {
                final String value = st.nextToken();
                log.debug("processing {}={} query", key, value);
                if (!"title".equals(key)) {
                    log.error("Search by title is allowed only");
                    return noProducts;
                }
                for (String id : products.keySet()) {
                    log.error("key: {} value:{} id:{}", key, value, id);
                    ProductInformation pi = products.get(id);
                    if (pi.getTitle().startsWith(value)) {
                        pis.add(id);
                    }
                }
            }
        }
        return pis;
    }
}
