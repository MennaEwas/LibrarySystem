package com.code81.library_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import com.code81.library_system.service.UserService;

import com.code81.library_system.entities.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService){
        this.userService = userService;

    }


    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User dummyUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dummyUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        User dummyUser = userService.updateUser(user);
        return ResponseEntity.ok(dummyUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
