package com.tenpo.math.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    CustomUserDetailsService userService;

    //@Autowired
    //private DefaultTokenServices tokenServices;

    @PostMapping(path = "/signup", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signUpUser(@RequestBody AppUser user){
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("{}");
    }

    @DeleteMapping("/revoke")
    public ResponseEntity logout(Authentication authentication){
        final String userToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("{}");
        //tokenServices.revokeToken(userToken);
    }

}
