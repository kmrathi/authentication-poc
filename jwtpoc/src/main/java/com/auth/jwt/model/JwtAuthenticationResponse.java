package com.auth.jwt.model;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private String jwt ;

    public JwtAuthenticationResponse(String jwt) {
        this.jwt = jwt ;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
