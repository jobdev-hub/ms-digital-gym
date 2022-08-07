package br.com.jobdev.msdigitalgym;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Microservice: Digital Gym", version = "1.0.0"))
public class MsDigitalGymApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsDigitalGymApplication.class, args);
    }

}
