package com.pointofsales.miniproject.controller;

import com.pointofsales.miniproject.model.dto.AuthenticationRequest;
import com.pointofsales.miniproject.model.dto.AuthenticationResponse;
import com.pointofsales.miniproject.model.dto.RegisterRequest;
import com.pointofsales.miniproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("pos/auth")
public class LoginController {

    private final LoginService authServ;

    //@GetMapping("/test")
    //public ResponseEntity<String> test(){
    //    return ResponseEntity.ok("Test OK");
    //}

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest req){
        return ResponseEntity.ok(authServ.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest req){
        return ResponseEntity.ok(authServ.authenticate(req));
    }

    }
