package com.rest.microservices.core.docs;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class BaseSwaggerConfig {
    private final String basePackage;

    public BaseSwaggerConfig(String basePackage) {
        this.basePackage = basePackage;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Api Baseadas no concenito de micro serviços")
                .version("1.0")
                .build();
    }
}
