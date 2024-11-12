package com.example.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    @ConditionalOnProperty(name = "enableThisIsMyFirstConditionalBean", havingValue = "true")
    public String ThisIsMyFirstConditionalBean() {
        return "Conditional Bean is enabled!";
    }
}
