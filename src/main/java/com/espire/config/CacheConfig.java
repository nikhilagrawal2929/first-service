package com.espire.config;

import com.espire.practice.Employee;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    return   RedisCacheManager
                .builder(redisConnectionFactory)

                .transactionAware().build();

        // Fallback Cache Configuration
      //  ConcurrentMapCacheManager fallbackCacheManager = new ConcurrentMapCacheManager("dataCache");

        //return new CompositeCacheManager(builder.build(), fallbackCacheManager);
    }

   /* private RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }*/
    @Bean
    public CacheErrorHandler cacheErrorHandler() {
        return new RedisCacheErrorHandler();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Set serializer
        Jackson2JsonRedisSerializer<List> serializer = new Jackson2JsonRedisSerializer<>(List.class);
        template.setValueSerializer(serializer);
        template.setKeySerializer(RedisSerializer.string());

        return template;
    }
}
