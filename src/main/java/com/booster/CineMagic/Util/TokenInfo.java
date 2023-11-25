package com.booster.CineMagic.Util;

public class TokenInfo {
    private final String jwtToken;

    public TokenInfo(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }
}
