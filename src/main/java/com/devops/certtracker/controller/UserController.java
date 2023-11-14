package com.devops.certtracker.controller;

import com.devops.certtracker.dto.request.ChangePasswordRequest;
import com.devops.certtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userservice;
    @PatchMapping("/change-password")
    ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal authenticatedUser){
        userservice.changePassword(request, authenticatedUser);
        return  ResponseEntity.ok().build();
    }

}
