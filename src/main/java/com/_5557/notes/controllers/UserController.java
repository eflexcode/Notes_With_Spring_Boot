package com._5557.notes.controllers;

import com._5557.notes.model.Response;
import com._5557.notes.model.User;
import com._5557.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/delete/{emailId}")
    public ResponseEntity<Response> deleteUser(@PathVariable String  emailId) {

        return ResponseEntity.ok(userService.deleteUser(emailId));
    }

    @GetMapping("/get/{emailId}")
    public ResponseEntity<User> getUserWithId(@PathVariable String  emailId)  {

        return ResponseEntity.ok(userService.getUserWithId(emailId));
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateUserWithId(@RequestBody User  user)  {

        return ResponseEntity.ok(userService.updateUserWithId(user));
    }

}
