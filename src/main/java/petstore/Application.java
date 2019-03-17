package petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import petstore.service.PetStoreService;
import petstore.service.impl.PetStoreInMemoryDatabaseService;
import petstore.service.impl.PetStoreServiceImpl;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PetStoreService getPetstoreServiceImpl() {

        return new PetStoreServiceImpl(new PetStoreInMemoryDatabaseService());
    }
}
