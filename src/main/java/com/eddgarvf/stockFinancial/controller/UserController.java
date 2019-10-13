package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.model.User;
import com.eddgarvf.stockFinancial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/get/all")
    public List<User> getAllUsers(@RequestHeader("Authorization") String bearer){

        return userService.getAllUsers();
    }

    @GetMapping(path = "/get/{userId}")
    public User getUserById(@PathVariable(name = "userId") int userId){
        return userService.getUserById(userId);
    }

    @PostMapping(path = "/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping(path = "/update")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping(path = "/delete/{userId}")
    public void deleteUser(@PathVariable(name = "userId") int userId){
        userService.deleteUser(userId);
    }

}
