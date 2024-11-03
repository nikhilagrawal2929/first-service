/*
package com.espire.config;

import com.espire.service.ExternalServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "second-service", fallback = ExternalServiceFallback.class)
//@FeignClient(name = "second-service", url = "http://external-service-url", fallback = ExternalServiceFallback.class)
public interface ExternalServiceClient {
    @GetMapping("/api/objects")
    List<String> getObjects();
}
*/
