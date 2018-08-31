// START SNIPPET ResourceBasedProductLookup_head
package packt.java11.mybusiness.productinformation.lookup;
//SNIPPET SKIP TILL "//IMPORT"

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import packt.java11.mybusiness.productinformation.ProductInformation;
import packt.java11.mybusiness.productinformation.ProductLookup;

import java.io.IOException;
import java.util.*;

//IMPORT

@Service
public class ResourceBasedProductLookup implements ProductLookup {
//END SNIPPET
// START SNIPPET ResourceBasedProductLookup_noProduct
    private static final List<String> noProducts = new LinkedList<>();
//END SNIPPET
    private static Logger log = LoggerFactory.getLogger(ResourceBasedProductLookup.class);
// START SNIPPET ResourceBasedProductLookup_map
    final private Map<String, ProductInformation> products = new HashMap<>();
//END SNIPPET
    private boolean productsAreNotLoaded = true;

    // START SNIPPET ResourceBasedProductLookup_fromProperties
    private ProductInformation fromProperties(Properties properties) {
        final ProductInformation pi = new ProductInformation();
        pi.setTitle(properties.getProperty("title"));
        pi.setDescription(properties.getProperty("description"));
        pi.setWeight(Double.parseDouble(properties.getProperty("weight")));
        pi.getSize()[0] = Double.parseDouble(properties.getProperty("width"));
        pi.getSize()[1] = Double.parseDouble(properties.getProperty("height"));
        pi.getSize()[2] = Double.parseDouble(properties.getProperty("depth"));
        return pi;
    }
    //END SNIPPET

    // START SNIPPET ResourceBasedProductLookup_loadProducts
    private void loadProducts() {
        if (productsAreNotLoaded) {
            try {
                Resource[] resources =
                    new PathMatchingResourcePatternResolver()
                        .getResources("classpath:products/*.properties");
                for (Resource resource : resources) {
                    loadResource(resource);
                }
                productsAreNotLoaded = false;
            } catch (IOException ex) {
                log.error("Test resources can not be read", ex);
            }
        }
    }
    //END SNIPPET

    // START SNIPPET ResourceBasedProductLookup_loadResource
    private void loadResource(Resource resource) throws IOException {
        final int dotPos = resource.getFilename().lastIndexOf('.');
        final String id = resource.getFilename().substring(0, dotPos);
        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        final ProductInformation pi = fromProperties(properties);
        pi.setId(id);
        products.put(id, pi);
    }
    //END SNIPPET

    // START SNIPPET ResourceBasedProductLookup_byId
    @Override
    public ProductInformation byId(String id) {
        loadProducts();
        if (products.containsKey(id)) {
            return products.get(id);
        } else {
            return ProductInformation.emptyProductInformation;
        }
    }
    //END SNIPPET

    // START SNIPPET ResourceBasedProductLookup_byQuery
    @Override
    public List<String> byQuery(String query) {
        // SNIPPET SKIP 1 LINE
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
    //END SNIPPET
}
