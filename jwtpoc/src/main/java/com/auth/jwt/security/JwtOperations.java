package com.auth.jwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.attribute.UserPrincipal;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtOperations {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtOperations.class);

    @Value("${jwt.secret.key}")
    private String secretKey ;
    @Value("${validity.millis.seconds}")
    private long validityInMilliseconds ;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateJwtToken(String userName){
        LOGGER.info("generating Jwt token");

        Claims claims = Jwts.claims().setSubject(userName);
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMilliseconds);
        String jwtToken = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();

        return jwtToken ;

    }

    public boolean validateToken(final String token){
        if(isTokenExpired(token)) {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true ;
        }
        return false ;
    }

    private boolean isTokenExpired(String token) {
        if(token!=null){
            Date expiry = getExpiryFromToken(token);
            return expiry.after(new Date());
        }
        return false;
    }

    private Date getExpiryFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public  <T>T getClaimFromToken(String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaisFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaisFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
