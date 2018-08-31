// START SNIPPET SpringConfigurationLocal
package packt.java11.mybusiness.productinformation;

//SNIPPET SKIP TILL "//IMPORT"

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import packt.java11.mybusiness.productinformation.lookup.ResourceBasedProductLookup;
//IMPORT

@Configuration
@Profile("local")
public class SpringConfigurationLocal {

    @Bean
    @Primary
    public ProductLookup productLookup() {
        return new ResourceBasedProductLookup();
    }

    @Bean
    public ProductInformationServiceUrlBuilder urlBuilder() {
        return null;
    }
}
//END SNIPPET