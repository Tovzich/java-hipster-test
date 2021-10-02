package org.example.model;

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

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonGetter("id_token")
    public String getIdToken() {
        return idToken;
    }

    @JsonSetter("id_token")
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}