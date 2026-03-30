package org.manish.authentication.controller;


import jakarta.validation.Valid;
import org.manish.authentication.Service.UserService;
import org.manish.authentication.dto.*;
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
    public ApiResponse<UserResponseDTO> registerUser(@Valid @RequestBody RegisterUserDTO user)  {

        return new ApiResponse<>(true,"Success", _userService.register(user));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> loginUser(@Valid @RequestBody LoginUserDTO user)  {

        return new ApiResponse<>(true,"Success", _userService.loginUser(user));
    }

}
