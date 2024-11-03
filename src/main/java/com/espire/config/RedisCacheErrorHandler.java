package com.espire.config;

import io.lettuce.core.RedisConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.stereotype.Component;

import java.net.ConnectException;

@Component
public class RedisCacheErrorHandler implements CacheErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheErrorHandler.class);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        logError("get", cache, key, exception);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        logError("put", cache, key, exception);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        logError("evict", cache, key, exception);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        logError("clear", cache, null, exception);
    }

    private void logError(String operation, Cache cache, Object key, RuntimeException exception) {
        if (exception instanceof RedisConnectionException || exception.getCause() instanceof ConnectException) {
            logger.error("Redis connection error during {} operation on cache {} for key {}: {}", operation, cache.getName(), key, exception.getMessage());
        } else {
            logger.error("Error during {} operation on cache {} for key {}: {}", operation, cache.getName(), key, exception.getMessage());
        }
    }
}
