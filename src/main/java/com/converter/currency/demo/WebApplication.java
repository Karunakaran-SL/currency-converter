package com.converter.currency.demo;

import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.converter.currency.demo.config.CurrencyConfiguration;
import com.converter.currency.demo.model.Country;
import com.converter.currency.demo.model.User;
import com.converter.currency.demo.repository.RoleRepository;
import com.converter.currency.demo.repository.UserRepository;

@SpringBootApplication
@SuppressWarnings({"deprecation","squid:CallToDeprecatedMethod"})
public class WebApplication extends SpringBootServletInitializer{
	private static final String DEFAULT_TEST_USER = "test123";
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
    
    @Bean
    public CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        List<User> hasUserEntry = userRepository.findAll();
        if(hasUserEntry ==null || hasUserEntry.isEmpty()){
            User user = new User();
            user.setUsername(DEFAULT_TEST_USER);
            user.setPasswordConfirm(DEFAULT_TEST_USER);
            user.setAddress(DEFAULT_TEST_USER);
            user.setCity(DEFAULT_TEST_USER);
            user.setCountry(Country.US);
            user.setDob(DEFAULT_TEST_USER);
            user.setEmail(DEFAULT_TEST_USER+"@gmail.com");
            user.setZipCode(DEFAULT_TEST_USER);
            user.setPassword(bCryptPasswordEncoder.encode(DEFAULT_TEST_USER));
            user.setRoles(new HashSet<>(roleRepository.findAll()));
            userRepository.save(user);
        }
        return null;
    }
    
    @Bean
    @ConfigurationProperties(prefix="currency.cache")
    public CurrencyConfiguration currencyConfiguration(){
    	return new CurrencyConfiguration();
    }
    
}
