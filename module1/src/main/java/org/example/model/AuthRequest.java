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

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter("rememberMe")
    public Boolean getRememberMe() {
        return rememberMe;
    }

    @JsonSetter("rememberMe")
    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @JsonGetter("username")
    public String getUsername() {
        return username;
    }

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username;
    }
}