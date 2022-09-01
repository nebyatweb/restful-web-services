package com.nebyatweb.rest.webservices.restfulwebservices.controller;

import com.nebyatweb.rest.webservices.restfulwebservices.domain.User;
import com.nebyatweb.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.nebyatweb.rest.webservices.restfulwebservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User retrieveUser(@PathVariable int id) {
        User user = userService.findOne(id);
        if(user == null)
            throw new UserNotFoundException("User with id: " + id + " cannot be found!");
        return user;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable int id) {
        userService.deleteById(id);
    }
}
