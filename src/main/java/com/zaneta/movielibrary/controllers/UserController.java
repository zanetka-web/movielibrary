package com.zaneta.movielibrary.controllers;


import com.zaneta.movielibrary.config.ResponseConfig;
import com.zaneta.movielibrary.models.User;
import com.zaneta.movielibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody User user) {
        String result = null;

        if (user != null && user.getUsername() != null && user.getName() != null
                && user.getEmail() != null && user.getPassword() != null && user.getType() != null) {
            result = userService.createNewUer(user);
        }

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseConfig.getResponse(HttpStatus.BAD_REQUEST, "user is not created");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {
        User result = userService.getById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseConfig.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateNameAndEmail(@PathVariable("id") int id, @RequestBody
    User update) {
        String result = userService.updateNameAndEmail(id, update);

        if (result.equals("did not update")) {
            return ResponseConfig.getResponse(HttpStatus.NOT_FOUND, result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/{id}/password")
    public ResponseEntity changePassword(@PathVariable("id") int id, @RequestBody
    HashMap<String, String> passwords) {
        String previous = passwords.get("previous");
        String newPassword = passwords.get("newPassword");

        String result = userService.changePassword(id, previous, newPassword);
        if (result.equals("password updated")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseConfig.getResponse(HttpStatus.NOT_FOUND, result);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id, @RequestBody
    HashMap<String, String> adminInfo) {
        String username = adminInfo.get("username");
        String password = adminInfo.get("password");

        String result = userService.deleteUser(id, username, password);
        if (result.equals("user is not found")) {
            return ResponseConfig.getResponse(HttpStatus.NOT_FOUND, result);
        } else {
            return ResponseEntity.ok(result);
        }

    }

}
