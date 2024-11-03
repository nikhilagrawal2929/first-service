package com.espire.service;

import com.espire.practice.Employee;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MyService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackGetUser")
    public List<Employee> getMyObjects() {
        try {
            // Attempt to fetch employees from Redis
            List<Employee> employees = (List<Employee>) redisTemplate.opsForValue().get("all-users1");

            if (employees != null) {
                log.info("Users fetched from Redis: {}", employees);
                return employees;
            } else {
                log.info("No users found in Redis. Fetching from DB...");
                employees = fetchEmployeesFromDatabase();
                // Save to Redis for future calls
                redisTemplate.opsForValue().set("all-users1", employees);
                return employees;
            }
        } catch (Exception e) {
            log.error("Error accessing Redis: {}", e.getMessage());
            throw e; // Re-throw the exception to trigger the circuit breaker
        }
    }

    private List<Employee> fetchEmployeesFromDatabase() {
        // This method encapsulates the logic for fetching employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Nikhil"));
        employees.add(new Employee(102, "XYZ"));
        employees.add(new Employee(103, "Kishor"));
        employees.add(new Employee(104, "Dhiraaj"));
        employees.add(new Employee(105, "Harsh"));
        return employees;
    }

    public List<Employee> fallbackGetUser(Throwable throwable) {
        log.error("Fallback method is called");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Nikhil"));
        return employees; // or handle as needed
    }

    @CacheEvict(value = "dataCache", key = "'all-users1'")
    public void evictCache() {
        redisTemplate.delete("all-users1");
        log.info("Cache for 'all-users' evicted.");
    }
}
