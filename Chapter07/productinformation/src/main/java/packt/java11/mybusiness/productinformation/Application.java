// START SNIPPET Application
package packt.java11.mybusiness.productinformation;

//SNIPPET SKIP TILL "//IMPORT"

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import packt.java11.mybusiness.SpringScanBase;
//IMPORT

@SpringBootApplication(scanBasePackageClasses = SpringScanBase.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// END SNIPPET