package com.rest.microservices.gatewayrest;

import com.rest.microservices.core.property.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("com.rest.microservices")
public class GatewayRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayRestApplication.class, args);
    }

}
