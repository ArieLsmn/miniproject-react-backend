package com.pointofsales.miniproject.service;

import com.pointofsales.miniproject.model.entity.Role;
import com.pointofsales.miniproject.model.entity.User;
import com.pointofsales.miniproject.model.dto.AuthenticationRequest;
import com.pointofsales.miniproject.model.dto.AuthenticationResponse;
import com.pointofsales.miniproject.model.dto.RegisterRequest;
import com.pointofsales.miniproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepo;

    //List<User> userRepo;

    private final PasswordEncoder passEnc;
    private final JwtService jwtServ;
    private final AuthenticationManager authManager;


    public AuthenticationResponse register(RegisterRequest req){
        var user = User.builder()
                .username(req.getUsername())
                .password(passEnc.encode(req.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        var jwtToken = jwtServ.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest req){

        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                req.getUsername(),req.getPassword()
        ));

        var user = userRepo.findByUsername(req.getUsername()).orElseThrow();
        var jwtToken = jwtServ.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
