package org.example;

import org.assertj.core.api.SoftAssertions;
import org.example.controller.RetrofitController;
import org.example.model.*;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class CountryAndRegionTests {
    @Test
    void shouldPostRegion() throws IOException {
        RegionModelRequest regionModelRequest = new RegionModelRequest();
        final String regionName = "Skyrim_" + (int) (Math.random() * 1000);
        regionModelRequest.setRegionName(regionName);

        Response<RegionModelResponse> callPostRegion = RetrofitController
                .getRegionControllerApi()
                .postRegion(regionModelRequest)
                .execute();
        assertThat(callPostRegion.code()).isEqualTo(SC_CREATED);
        assertThat(callPostRegion.body()).isNotNull();
        final Integer regionId = callPostRegion.body().getId();

        SoftAssertions.assertSoftly(softly -> {
            assertThat(callPostRegion.body().getId())
                    .isNotZero()
                    .isPositive();
            assertThat(callPostRegion.body().getRegionName()).isEqualTo(regionName);
            assertThat(callPostRegion.body().getAdditionalProperties()).isEmpty();
        });

        Response<RegionModelResponse> callGetRegionAfterPost = RetrofitController
                .getRegionControllerApi()
                .getRegionById(regionId)
                .execute();
        assertThat(callGetRegionAfterPost.code()).isEqualTo(SC_OK);
        assertThat(callGetRegionAfterPost.body()).isNotNull();
        SoftAssertions.assertSoftly(softly -> {
            assertThat(callGetRegionAfterPost.body().getId()).isEqualTo(regionId);
            assertThat(callGetRegionAfterPost.body().getRegionName()).isEqualTo(regionName);
            assertThat(callGetRegionAfterPost.body().getAdditionalProperties()).isEmpty();
        });

        Response<RegionModelResponse> callDeleteRegion = RetrofitController
                .getRegionControllerApi()
                .deleteRegionById(regionId)
                .execute();
        assertThat(callDeleteRegion.code()).isEqualTo(SC_NO_CONTENT);

        Response<RegionModelResponse> callGetRegionAfterDelete = RetrofitController
                .getRegionControllerApi()
                .getRegionById(regionId)
                .execute();
        assertThat(callGetRegionAfterDelete.code()).isEqualTo(SC_NOT_FOUND);
    }

    @Test
    void shouldPostCountry() throws IOException {
        RegionModelRequest regionModelRequest = new RegionModelRequest();
        final String regionName = "Skyrim_" + (int) (Math.random() * 1000);
        regionModelRequest.setRegionName(regionName);

        Response<RegionModelResponse> callPostRegion = RetrofitController
                .getRegionControllerApi()
                .postRegion(regionModelRequest)
                .execute();
        assertThat(callPostRegion.code()).isEqualTo(SC_CREATED);
        assertThat(callPostRegion.body()).isNotNull();
        final Integer regionId = callPostRegion.body().getId();

        SoftAssertions.assertSoftly(softly -> {
            assertThat(callPostRegion.body().getId())
                    .isNotZero()
                    .isPositive();
            assertThat(callPostRegion.body().getRegionName()).isEqualTo(regionName);
            assertThat(callPostRegion.body().getAdditionalProperties()).isEmpty();
        });

        CountryModelRequest countryModelRequest = new CountryModelRequest();
        final String countryName = "Tamriel_" + (int) (Math.random() * 1000);
        countryModelRequest.setCountryName(countryName);


        countryModelRequest.setRegionId(regionId.toString());
        countryModelRequest.setRegion(callPostRegion.body());

        Response<CountryModelResponse> callPostCountry = RetrofitController
                .getCountryControllerApi()
                .postCountry(countryModelRequest)
                .execute();
        assertThat(callPostCountry.code()).isEqualTo(SC_CREATED);
        assertThat(callPostCountry.body()).isNotNull();
        final Integer countryId = callPostCountry.body().getId();

        SoftAssertions.assertSoftly(softly -> {
            assertThat(callPostCountry.body().getId())
                    .isNotZero()
                    .isPositive();
            assertThat(callPostCountry.body().getCountryName()).isEqualTo(countryName);
            assertThat(callPostCountry.body().getRegion().getRegionName()).isEqualTo(regionName);
            assertThat(callPostCountry.body().getRegion().getId()).isEqualTo(regionId);
            assertThat(callPostCountry.body().getRegion().getAdditionalProperties()).isEmpty();
            assertThat(callPostCountry.body().getAdditionalProperties()).isEmpty();
        });


        Response<CountryModelResponse> callGetAfterPostCountry = RetrofitController
                .getCountryControllerApi()
                .getCountryById(countryId)
                .execute();
        assertThat(callGetAfterPostCountry.code()).isEqualTo(SC_OK);
        assertThat(callGetAfterPostCountry.body()).isNotNull();
        SoftAssertions.assertSoftly(softly -> {
            assertThat(callGetAfterPostCountry.body().getId()).isEqualTo(countryId);
            assertThat(callGetAfterPostCountry.body().getCountryName()).isEqualTo(countryName);
            assertThat(callGetAfterPostCountry.body().getRegion().getRegionName()).isEqualTo(regionName);
            assertThat(callGetAfterPostCountry.body().getRegion().getId()).isEqualTo(regionId);
            assertThat(callGetAfterPostCountry.body().getRegion().getAdditionalProperties()).isEmpty();
            assertThat(callGetAfterPostCountry.body().getAdditionalProperties()).isEmpty();
        });

        Response<CountryModelResponse> callDeleteCountry = RetrofitController
                .getCountryControllerApi()
                .deleteCountryById(countryId).execute();
        SoftAssertions.assertSoftly(softly -> assertThat(callDeleteCountry.code()).isEqualTo(SC_NO_CONTENT));

        Response<CountryModelResponse> callGetAfterDeleteCountry = RetrofitController
                .getCountryControllerApi()
                .getCountryById(countryId).execute();
        SoftAssertions.assertSoftly(softly -> assertThat(callGetAfterDeleteCountry.code()).isEqualTo(SC_NOT_FOUND));

        Response<RegionModelResponse> callDeleteRegion = RetrofitController
                .getRegionControllerApi()
                .deleteRegionById(regionId)
                .execute();
        assertThat(callDeleteRegion.code()).isEqualTo(SC_NO_CONTENT);

        Response<RegionModelResponse> callGetAfterDeleteRegion = RetrofitController
                .getRegionControllerApi()
                .getRegionById(regionId)
                .execute();
        assertThat(callGetAfterDeleteRegion.code()).isEqualTo(SC_NOT_FOUND);
    }
}