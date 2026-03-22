package org.manish.authentication.controller;

import org.manish.authentication.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/")
    public String welcome(){
        return demoService.demoService();
//        return "Har har mahadev!";
    }
}
