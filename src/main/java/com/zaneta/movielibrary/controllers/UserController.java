package com.zaneta.movielibrary.controllers;


import com.zaneta.movielibrary.models.Movie;
import com.zaneta.movielibrary.models.User;
import com.zaneta.movielibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        return userService.createNewUer(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") int id) {return userService.getById(id);}


    @PatchMapping("/{id}")
    public String updateNameAndEmail(@PathVariable("id") int id, @RequestBody
    User update) {
        return userService.updateNameAndEmail(id, update);
    }

    @PostMapping("/{id}/password")
    public String changePassword(@PathVariable("id") int id, @RequestBody
    HashMap<String, String> passwords) {
        String previous = passwords.get("previous");
        String newPassword = passwords.get("newPassword");

        return userService.changePassword(id, previous,newPassword);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id, @RequestBody
    HashMap<String, String> adminInfo){
      String username = adminInfo.get("username");
      String password = adminInfo.get("password");

        return userService.deleteUser(id, username, password);
    }

}
