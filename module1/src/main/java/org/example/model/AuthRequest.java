package org.example.model;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "password",
        "rememberMe",
        "username"
})
public class AuthRequest {
    @JsonProperty("password")
    private String password;

    @JsonProperty("rememberMe")
    private Boolean rememberMe;

    @JsonProperty("username")
    private String username;

    public String getPassword() {
        return password;
    }

    public AuthRequest password(String password) {
        this.password = password;
        return this;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public AuthRequest rememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AuthRequest username(String username) {
        this.username = username;
        return this;
    }
}