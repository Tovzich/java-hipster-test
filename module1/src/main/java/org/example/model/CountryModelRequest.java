package org.example.model;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countryName",
        "regionId"
})
public class CountryModelRequest {
    @JsonProperty("countryName")
    private String countryName;

    @JsonProperty("regionId")
    private String regionId;

    @JsonProperty("region")
    private RegionModelResponse region;

    @JsonGetter("countryName")
    public String getCountryName() {
        return countryName;
    }

    @JsonSetter("countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonGetter("regionId")
    public String getRegionId() {
        return regionId;
    }

    @JsonSetter("regionId")
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @JsonGetter("region")
    public RegionModelResponse getRegion() {
        return region;
    }

    @JsonSetter("region")
    public void setRegion(RegionModelResponse region) {
        this.region = region;
    }
}