// START SNIPPET ProductLookup
package packt.java11.mybusiness.productinformation;

import java.util.List;

public interface ProductLookup {
    ProductInformation byId(String id);

    List<String> byQuery(String query);
}
//END SNIPPET