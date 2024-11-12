package com.example.controller;


import com.example.models.Ticket;
import com.example.models.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long id){
        return userService.getUserById(id);
    }
}
