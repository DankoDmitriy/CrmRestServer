package com.danko.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServerSpringBootApplication {

    private final static String LOCALIZATION_PATCH = "localization/error_messages";
    private final static String DEFAULT_ENCODING = "UTF-8";

    public static void main(String[] args) {
        SpringApplication.run(ServerSpringBootApplication.class);
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(LOCALIZATION_PATCH);
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
