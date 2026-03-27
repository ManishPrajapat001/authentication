package org.manish.authentication.controller;


import org.manish.authentication.Service.UserService;
import org.manish.authentication.dto.ApiResponse;
import org.manish.authentication.entity.User;
import org.manish.authentication.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService _userService;

    public UserController(UserService userService) {
        _userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody UserDTO user)  {

        return new ApiResponse(true,"Success", _userService.register(user));
    }

    @PostMapping("/login")
    public ApiResponse loginUser(@RequestBody UserDTO user)  {

        return new ApiResponse(true,"Success", _userService.loginuser(user));
    }

}
