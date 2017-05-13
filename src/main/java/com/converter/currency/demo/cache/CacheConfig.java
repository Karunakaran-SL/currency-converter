package com.converter.currency.demo.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.converter.currency.demo.config.CurrencyConfiguration;
import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CacheConfig {
	
   @Autowired
   private CurrencyConfiguration configuration;
   public static final String EXTERNAL_REQUEST = "external";

   @Bean
   public Cache external() {
      return new GuavaCache(EXTERNAL_REQUEST, CacheBuilder.newBuilder()
            .expireAfterWrite(configuration.getTtl(), TimeUnit.SECONDS)
            .build());
   }
}
