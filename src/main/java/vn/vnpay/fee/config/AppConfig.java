package vn.vnpay.fee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fee API for khdn")
                        .description("Customer Service of VNPAY")
                        .contact(new Contact()
                                .email("Hidden@vnpay.vn")
                                .name("Vnpay")
                                .url("https://Vnpay.vn"))
                        .license(new License()
                                .name("Vnpay")
                                .url("https://Vnpay.vn"))
                        .version("1.0.0"));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
