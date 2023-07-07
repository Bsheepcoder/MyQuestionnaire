package com.computer.network.controller;

import com.computer.network.service.UserService;
import com.computer.network.pojo.BaseResponse;
import com.computer.network.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public BaseResponse addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user) {
        return userService.login(user);
    }


}
