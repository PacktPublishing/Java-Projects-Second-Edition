package packt.java11.mybusiness.productinformation.lookup;

import org.junit.Assert;
import org.junit.Test;
import packt.java11.mybusiness.productinformation.ProductLookup;

import java.util.List;

public class ResourceBasedProductLookupTest {

    @Test
    public void queryFindsMultipleTitles(){
        ProductLookup pl = new ResourceBasedProductLookup();
        List<String> pis = pl.byQuery("title=D&title=B");
        Assert.assertEquals(pis.size(),3);
    }
}
