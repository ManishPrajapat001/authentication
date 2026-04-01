package org.manish.authentication.controller;

import org.manish.authentication.Service.DemoService;
import org.manish.authentication.dto.ApiResponse;
import org.manish.authentication.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    @Autowired
    final DemoService demoService;

    public demoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/")
    public ApiResponse<String> welcome(){
        return new ApiResponse<>(true,"Success", demoService.demoService());

//        return demoService.demoService();
//        return "Har har mahadev!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin access";
    }

    @GetMapping("/user")
    public String user() {
        return "User access";
    }
}
