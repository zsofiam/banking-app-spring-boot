package com.example.banking_app.controller;

import com.example.banking_app.model.User;
import com.example.banking_app.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{user_id}")
    public User getUtilityAccount(@PathVariable("user_id") Long user_id) {
        return userService.getUserById(user_id);
    }

    @PostMapping("")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long user_id) {
        userService.deleteUser(user_id);
    }
}
