package packt.java11.bulkorder;

import packt.java11.bulkorder.dtos.ProductInformation;

import java.util.List;

public interface ProductLookup {

    ProductInformation byId(String id);

    List<String> byQuery(String query);
}
