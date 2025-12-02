package com.finance.personalfinance.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-controller")
public class DemoController {

    @GetMapping("/authenticated")
    public ResponseEntity<String> authenticated() {
        return ResponseEntity.ok("Authenticated login: visible to any logged in user");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("ADMIN Access: Content visible only to users with the ADMIN role");
    }

    @GetMapping("/user")
    public ResponseEntity<String> userAccess() {
        return ResponseEntity.ok("USER Access: Content visible only to users with the USER role");
    }
}