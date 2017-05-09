package com.converter.currency.demo;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.converter.currency.demo.model.Country;
import com.converter.currency.demo.model.Role;
import com.converter.currency.demo.model.User;
import com.converter.currency.demo.repository.RoleRepository;
import com.converter.currency.demo.repository.UserRepository;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        List<User> hasUserEntry = userRepository.findAll();
        if(hasUserEntry ==null || hasUserEntry.isEmpty()){
            User user = new User();
            user.setUsername("test123");
            user.setPasswordConfirm("test123");
            user.setAddress("test123");
            user.setCity("test123");
            user.setCountry(Country.US);
            user.setDob("test123");
            user.setEmail("test123@gmail.com");
            user.setZipCode("test123");
            user.setPassword(bCryptPasswordEncoder.encode("test123"));
            user.setRoles(new HashSet<>(roleRepository.findAll()));
            userRepository.save(user);
        }
        return null;
    }
    
}
