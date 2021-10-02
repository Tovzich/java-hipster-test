package org.example.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "regionName"
})
public class RegionModelResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("regionName")
    private String regionName;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonGetter("id")
    public Integer getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonGetter("regionName")
    public String getRegionName() {
        return regionName;
    }

    @JsonSetter("regionName")
    public void setRegionName(String regionName) {
        this.regionName = regionName;
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