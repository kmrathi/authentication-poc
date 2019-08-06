package com.auth.jwt.rest;

import com.auth.jwt.dao.ApplicationUsersDao;
import com.auth.jwt.model.ApplicationUser;
import com.auth.jwt.model.JwtAuthenticationResponse;
import com.auth.jwt.model.LoginRequest;
import com.auth.jwt.security.JwtOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    ApplicationUsersDao applicationUsersDao ;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtOperations tokenProvider ;

    @GetMapping("/welcome")
    public String welcomeMessage(){
        return "Welcome - you are successfully authenticated" ;
    }

    @RequestMapping(path = {"/users"}, method = RequestMethod.GET)
    public List<ApplicationUser> getApplicationUsers(){
        return applicationUsersDao.findAll();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateJwtToken(authentication);
        response.setHeader("Authorization",jwt);
        return ResponseEntity.ok(jwt);
    }
}
