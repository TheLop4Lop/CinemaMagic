package com.booster.CineMagic.Util;

public class AuthenticationRequest {
    private static final long serialVersionUID = 1L;

    private String user;

    private String key;

    public AuthenticationRequest(String user, String key) {
        this.user = user;
        this.key = key;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
