package org.example.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countryName",
        "regionId"
})
public class CountryModel {
    @JsonProperty("countryName")
    private String countryName;

    @JsonProperty("regionId")
    private String regionId;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("region")
    private RegionModel region;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty("regionId")
    public String getRegionId() {
        return regionId;
    }

    @JsonProperty("regionId")
    public void setRegionId(String id) {
        this.regionId = regionId;
    }

    @JsonProperty("region")
    public RegionModel getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(RegionModel region) {
        this.region = region;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
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