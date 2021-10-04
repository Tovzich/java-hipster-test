package org.example.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "password",
        "rememberMe",
        "username"
})
public class AuthResponse {
    @JsonProperty("id_token")
    private String idToken;

    @JsonAnySetter
    private final Map<String, Object> additionalProperties = new HashMap<>();

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public Map<String, Object> getAdditionalProperties() {
        return Collections.unmodifiableMap(this.additionalProperties);
    }
}