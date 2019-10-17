package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.model.User;
import com.eddgarvf.stockFinancial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        try{
            return userService.getAll();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/{userId}")
    public User getUserById(@PathVariable(name = "userId") int userId){
        try {
            return userService.getById(userId);
        }catch(Exception e){
            return new User();
        }
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/add")
    public void addUser(@RequestBody User user){
        userService.add(user);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = "/update")
    public void updateUser(@RequestBody User user){
        userService.update(user);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(path = "/delete/{userId}")
    public void deleteUser(@PathVariable(name = "userId") int userId){
        userService.delete(userId);
    }

}
