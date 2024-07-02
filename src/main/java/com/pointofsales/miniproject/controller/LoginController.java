package com.pointofsales.miniproject.controller;

import com.pointofsales.miniproject.model.dto.AuthenticationRequest;
import com.pointofsales.miniproject.model.dto.AuthenticationResponse;
import com.pointofsales.miniproject.model.dto.RegisterRequest;
import com.pointofsales.miniproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> register(@RequestBody RegisterRequest req){

        Object body;
        HttpStatus st=HttpStatus.OK;;
        try{
            body=authServ.register(req);
        }catch (IllegalArgumentException e){
            st=HttpStatus.BAD_REQUEST;
            String er = "Invalid username";
            body=er;
        }

        //return ResponseEntity.ok(au);
        return ResponseEntity.status(st).body(body);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest req){
        Object body;
        HttpStatus st=HttpStatus.OK;;
        try{
            body=authServ.authenticate(req);

        }catch (IllegalArgumentException e){
            st=HttpStatus.BAD_REQUEST;
            String er = "Invalid username";
            body=er;
        }
        return ResponseEntity.status(st).body(body);
        //return ResponseEntity.ok(authServ.authenticate(req));

    }

}
