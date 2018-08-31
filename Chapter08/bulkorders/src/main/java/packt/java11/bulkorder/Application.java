package packt.java11.bulkorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackageClasses =
                packt.java11.by.example.mybusiness.SpringScanBase.class)
public class Application {
    public static void main(String[] args) {
        //new ClassLister().listClasses();
        SpringApplication.run(Application.class, args);
    }
}
