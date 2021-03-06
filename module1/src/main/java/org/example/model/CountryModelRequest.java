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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public RegionModelResponse getRegion() {
        return region;
    }

    public void setRegion(RegionModelResponse region) {
        this.region = region;
    }
}