package com.plazit.market.web.controller;


import com.plazit.market.domain.dto.AutheticationRequest;
import com.plazit.market.domain.dto.AutheticationResponse;
import com.plazit.market.domain.service.UserDetailsService;
import com.plazit.market.web.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AutheticationResponse> createToken(@RequestBody AutheticationRequest req){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUserName(),req.getPassword()));
            UserDetails userDetails = service.loadUserByUsername(req.getUserName());
            String jwt = jwtUtil.generarToken(userDetails);

            return new ResponseEntity<>(new AutheticationResponse(jwt), HttpStatus.OK);
        }catch (BadCredentialsException e){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

     }
}
