package com.workintech.S7D2.controller;

import com.workintech.S7D2.dto.RegistrationMember;
import com.workintech.S7D2.entity.Member;
import com.workintech.S7D2.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationService authenticationService;
  @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
     @PostMapping("/register")
    public Member register(@RequestBody RegistrationMember registrationMember){
        return  authenticationService.register(registrationMember.getEmail(),registrationMember.getPassword());
     }
    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        authenticationService.delete();
    }
}
