package com.converter.currency.demo.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cache")
public class CacheConfig {
   public int ttl = 20;
   public static final String EXTERNAL = "external";

   @Bean
   public Cache external() {
      return new GuavaCache(EXTERNAL, CacheBuilder.newBuilder()
            .expireAfterWrite(Integer.valueOf(ttl), TimeUnit.SECONDS)
            .build());
   }
}
