package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.model.User;
import com.eddgarvf.stockFinancial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/{userId}")
    public User getUserById(@PathVariable(name = "userId") int userId){
        return userService.getUserById(userId);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = "/update")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(path = "/delete/{userId}")
    public void deleteUser(@PathVariable(name = "userId") int userId){
        userService.deleteUser(userId);
    }

}
