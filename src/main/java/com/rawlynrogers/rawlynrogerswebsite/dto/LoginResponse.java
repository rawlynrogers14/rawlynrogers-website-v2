package com.rawlynrogers.rawlynrogerswebsite.dto;

public class LoginResponse {

    private String token;
    private long expiresInMs;

    public LoginResponse(String token, long expiresInMs) {
        this.token = token;
        this.expiresInMs = expiresInMs;
    }

    public String getToken() {
        return token;
    }

    public long getExpiresInMs() {
        return expiresInMs;
    }
}