package com.auth.jwt.security.hist;

import com.auth.jwt.security.JwtOperations;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenHistory  {

    private static final Map<String, String> userNameJwtTokenMap = new ConcurrentHashMap<>();

    @Autowired
    private JwtOperations jwtOperations ;

    public void invalidateOldToken(String username, String token){
        userNameJwtTokenMap.put(username,token);
    }

    public boolean isTokenIssuedPreviously(String username, String token){
        String oldToken = userNameJwtTokenMap.get(username);
        Date issuedAtForOldToken = jwtOperations.getClaimFromToken(oldToken, Claims::getIssuedAt);
        Date issuedAtCurrentToken = jwtOperations.getClaimFromToken(token, Claims::getIssuedAt);
        if(issuedAtCurrentToken.equals(issuedAtForOldToken)){
            return true ;
        }else{
            return false ;
        }
    }

}
