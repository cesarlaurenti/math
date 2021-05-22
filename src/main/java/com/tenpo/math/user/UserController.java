package com.tenpo.math.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    ICustomUserDetailService customUserDetailsService;

    @Autowired
    public UserController(CustomUserDetailsService userService){
        this.customUserDetailsService = userService;
    };

    @PostMapping(path = "/signup", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signUpUser(@Valid @RequestBody SignupUserRequest user){
        customUserDetailsService.registerUser(new AppUser(user.getUsername(), user.getPassword(), user.getRoles(), user.isActive()));
        return ResponseEntity.status(HttpStatus.CREATED).body("{}");
    }

    @DeleteMapping("/revoke")
    public ResponseEntity logout(Authentication authentication){
        //implementar cuando haya frontend
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("{}");
    }

}
