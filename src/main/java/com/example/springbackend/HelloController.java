package com.example.springbackend;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello(@RequestParam String name) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello " + name);
        return response;
    }

    @GetMapping("/hello/{name}")
    public Map<String, String> helloPath(@PathVariable String name) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello " + name);
        return response;
    }

    @PostMapping("/hello")
    public Map<String, String> helloPost(@RequestBody Map<String, String> request) {

        Map<String, String> response = new HashMap<>();

        response.put("message", "Hello " + request.get("name"));

        return response;
    }
}