package com.customer.relationship.controller;

import com.customer.relationship.Repository.UserRepository;
import com.customer.relationship.exception.UserNotFoundException;
import com.customer.relationship.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class userController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/users")
    public Page<Users> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUser(@PathVariable (value = "id") long id) throws UserNotFoundException {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("ID: " + id + " not found"));
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/users")
    public Users addUser(@RequestBody Users users) {
        return userRepository.save(users);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, String> deleteUser(@PathVariable(value = "id") long id) throws UserNotFoundException {
        Users users = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("ID: " + id + " not found"));
        userRepository.delete(users);
        Map<String, String> deleteUser = new HashMap<>();
        deleteUser.put("ID: ", id + " deleted");
        return deleteUser;
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable(value = "id") long id, @RequestBody Users userDetails) throws UserNotFoundException {
        Users users = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("ID: " + id + " not found"));
        users.setName(userDetails.getName());
        users.setEmail(userDetails.getEmail());
        users.setPhone(userDetails.getPhone());
        final Users updateUser = userRepository.save(users);
        return ResponseEntity.ok(updateUser);
    }
}
