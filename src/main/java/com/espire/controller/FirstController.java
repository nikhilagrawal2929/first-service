package com.espire.controller;

import com.espire.practice.Employee;
import com.espire.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class FirstController {
    @Autowired
    MyService myService;

    @GetMapping("/users")
    public ResponseEntity test() {
       List list = myService.getMyObjects();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @DeleteMapping("/evict-cache")
    public String evictCache() {
        myService.evictCache();
        return "Cache evicted successfully.";
    }

/*    @GetMapping("/testApi")
    public List test1() {
        return myService.getMyObjects();
    }*/
}
