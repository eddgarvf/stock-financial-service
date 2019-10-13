package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.jwt.JwtUtil;
import com.eddgarvf.stockFinancial.jwt.model.JwtRequest;
import com.eddgarvf.stockFinancial.jwt.model.JwtResponse;
import com.eddgarvf.stockFinancial.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/jwt")
public class JwtController {

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public JwtController(JwtUtil jwtUtil,
                         AuthenticationManager authenticationManager,
                         JwtUserDetailsService jwtUserDetailsService){
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<JwtResponse> generateJwt(@RequestBody JwtRequest jwtRequest){
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
