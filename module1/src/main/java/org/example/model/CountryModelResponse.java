package org.example.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countryName",
        "id",
        "region"
})
public class CountryModelResponse {
    @JsonProperty("countryName")
    private String countryName;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("region")
    private RegionModelResponse region;

    @JsonAnySetter
    private final Map<String, Object> additionalProperties = new HashMap<>();

    public String getCountryName() {
        return countryName;
    }

    public RegionModelResponse getRegion() {
        return region;
    }

    public Integer getId() {
        return id;
    }

    public Map<String, Object> getAdditionalProperties() {
        return Collections.unmodifiableMap(this.additionalProperties);
    }
}