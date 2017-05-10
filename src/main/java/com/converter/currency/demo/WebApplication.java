package com.converter.currency.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@SuppressWarnings({"deprecation","squid:CallToDeprecatedMethod"})
public class WebApplication extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
    }
    
    @Bean(name = "messageSource")
    public MessageSource messageSource() {
       ReloadableResourceBundleMessageSource messageSource = 
           new ReloadableResourceBundleMessageSource();
       messageSource.setBasename("classpath:ValidationMessages");
       messageSource.setCacheSeconds(5);
       return messageSource;
    }
    
}
