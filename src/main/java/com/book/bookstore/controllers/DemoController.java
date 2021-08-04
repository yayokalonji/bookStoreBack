package com.book.bookstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/demo")
public class DemoController {

    @GetMapping("/message")
    public ResponseEntity<Map<String, String>> getMessage() {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Ok");
        map.put("status", "true");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
