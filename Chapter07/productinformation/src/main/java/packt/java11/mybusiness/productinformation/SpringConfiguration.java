// START SNIPPET SpringConfiguration
package packt.java11.mybusiness.productinformation;
//SNIPPET SKIP TILL "//IMPORT"

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import packt.java11.mybusiness.productinformation.lookup.RestClientProductLookup;

//IMPORT
@Configuration
@Profile("production")
public class SpringConfiguration {

    @Bean
    @Primary
    public ProductLookup productLookup() {
        return new RestClientProductLookup(urlBuilder());
    }

    @Bean
    public ProductInformationServiceUrlBuilder urlBuilder() {
        return new ProductInformationServiceUrlBuilder("http://localhost");
    }
}
// END SNIPPET