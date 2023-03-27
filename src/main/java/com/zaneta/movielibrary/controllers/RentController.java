package com.zaneta.movielibrary.controllers;


import com.zaneta.movielibrary.config.ResponseConfig;
import com.zaneta.movielibrary.models.*;
import com.zaneta.movielibrary.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    RentService rentService;

    @PostMapping("")
    public ResponseEntity rentByUserId(@RequestBody RentRequest request) {
        String result = rentService.rent(request);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseConfig.getResponse(HttpStatus.NOT_FOUND, "User or movie not found");
        }
    }

    @GetMapping("/report")
    public ResponseEntity getRentedInfo(@RequestBody
    HashMap<String, String> adminInfo) {
        String username = adminInfo.get("username");
        String password = adminInfo.get("password");
        RentReport result = rentService.getAllRantingDataForAllUsers(username, password);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseConfig.getResponse(HttpStatus.BAD_REQUEST, "bad request");
        }
    }

}
