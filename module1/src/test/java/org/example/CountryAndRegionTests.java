package org.example;

import org.assertj.core.api.SoftAssertions;
import org.example.controller.RetrofitController;
import org.example.model.*;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class CountryAndRegionTests {
    static final Map<String, String> headers = new HashMap<>();

    @BeforeAll
    static void beforeAll() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("admin");
        authRequest.setRememberMe(true);
        authRequest.setPassword("u7ljdajLNo7PsVw7");

        try {
            Response<AuthResponse> callPost = RetrofitController
                    .getAuthControllerApi()
                    .authenticate(authRequest)
                    .execute();
            headers.put("Authorization", "Bearer " + callPost.body().getIdToken());
        } catch (IOException e) {
            throw new RuntimeException("I/O Ошибка получения токена", e);
        } catch (NullPointerException e) {
            throw new RuntimeException("NPE Ошибка получения токена", e);
        }
    }

    @Test
    void shouldPostRegion() throws IOException {
        RegionModelRequest regionModelRequest = new RegionModelRequest();
        final String regionName = "Skyrim_" + (int) (Math.random() * 1000);
        regionModelRequest.setRegionName(regionName);

        Response<RegionModelResponse> callPostRegion = RetrofitController
                .getRegionControllerApi()
                .postRegion(headers, regionModelRequest)
                .execute();
        assertThat(callPostRegion.code()).isEqualTo(SC_CREATED);
        SoftAssertions.assertSoftly(softly -> {
            assertThat(callPostRegion.body().getId())
                    .isNotZero()
                    .isPositive();
            assertThat(callPostRegion.body().getRegionName()).isEqualTo(regionName);
            assertThat(callPostRegion.body().getAdditionalProperties()).isEmpty();
        });

        final Integer regionId;
        try {
            regionId = callPostRegion.body().getId();
        } catch (NullPointerException e) {
            throw new RuntimeException("NPE Ошибка получения id региона", e);
        }

        Response<RegionModelResponse> callGetRegionAfterPost = RetrofitController
                .getRegionControllerApi()
                .getRegionById(headers, regionId)
                .execute();
        SoftAssertions.assertSoftly(softly -> {
            assertThat(callGetRegionAfterPost.code()).isEqualTo(SC_OK);
            assertThat(callGetRegionAfterPost.body().getId()).isEqualTo(regionId);
            assertThat(callGetRegionAfterPost.body().getRegionName()).isEqualTo(regionName);
            assertThat(callGetRegionAfterPost.body().getAdditionalProperties()).isEmpty();
        });

        Response<RegionModelResponse> callDeleteRegion = RetrofitController
                .getRegionControllerApi()
                .deleteRegionById(headers, regionId)
                .execute();
        assertThat(callDeleteRegion.code()).isEqualTo(SC_NO_CONTENT);

        Response<RegionModelResponse> callGetRegionAfterDelete = RetrofitController
                .getRegionControllerApi()
                .getRegionById(headers, regionId)
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
                .postRegion(headers, regionModelRequest)
                .execute();
        assertThat(callPostRegion.code()).isEqualTo(SC_CREATED);
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

        final Integer regionId;
        try {
            regionId = callPostRegion.body().getId();
        } catch (NullPointerException e) {
            throw new RuntimeException("NPE Ошибка получения id региона", e);
        }

        countryModelRequest.setRegionId(regionId.toString());
        countryModelRequest.setRegion(callPostRegion.body());

        Response<CountryModelResponse> callPostCountry = RetrofitController
                .getCountryControllerApi()
                .postCountry(headers, countryModelRequest)
                .execute();
        SoftAssertions.assertSoftly(softly -> {
            assertThat(callPostCountry.code()).isEqualTo(SC_CREATED);
            assertThat(callPostCountry.body().getId())
                    .isNotZero()
                    .isPositive();
            assertThat(callPostCountry.body().getCountryName()).isEqualTo(countryName);
            assertThat(callPostCountry.body().getRegion().getRegionName()).isEqualTo(regionName);
            assertThat(callPostCountry.body().getRegion().getId()).isEqualTo(regionId);
            assertThat(callPostCountry.body().getRegion().getAdditionalProperties()).isEmpty();
            assertThat(callPostCountry.body().getAdditionalProperties()).isEmpty();
        });

        final Integer countryId;
        try {
            countryId = callPostCountry.body().getId();
        } catch (Exception e) {
            throw new RuntimeException("NPE Ошибка получения id страны", e);
        }

        Response<CountryModelResponse> callGetAfterPostCountry = RetrofitController
                .getCountryControllerApi()
                .getCountryById(headers, countryId)
                .execute();
        SoftAssertions.assertSoftly(softly -> {
            assertThat(callGetAfterPostCountry.code()).isEqualTo(SC_OK);
            assertThat(callGetAfterPostCountry.body().getId()).isEqualTo(countryId);
            assertThat(callGetAfterPostCountry.body().getCountryName()).isEqualTo(countryName);
            assertThat(callGetAfterPostCountry.body().getRegion().getRegionName()).isEqualTo(regionName);
            assertThat(callGetAfterPostCountry.body().getRegion().getId()).isEqualTo(regionId);
            assertThat(callGetAfterPostCountry.body().getRegion().getAdditionalProperties()).isEmpty();
            assertThat(callGetAfterPostCountry.body().getAdditionalProperties()).isEmpty();
        });

        Response<CountryModelResponse> callDeleteCountry = RetrofitController
                .getCountryControllerApi()
                .deleteCountryById(headers, countryId).execute();
        SoftAssertions.assertSoftly(softly -> assertThat(callDeleteCountry.code()).isEqualTo(SC_NO_CONTENT));

        Response<CountryModelResponse> callGetAfterDeleteCountry = RetrofitController
                .getCountryControllerApi()
                .getCountryById(headers, countryId).execute();
        SoftAssertions.assertSoftly(softly -> assertThat(callGetAfterDeleteCountry.code()).isEqualTo(SC_NOT_FOUND));

        Response<RegionModelResponse> callDeleteRegion = RetrofitController
                .getRegionControllerApi()
                .deleteRegionById(headers, regionId)
                .execute();
        assertThat(callDeleteRegion.code()).isEqualTo(SC_NO_CONTENT);

        Response<RegionModelResponse> callGetAfterDeleteRegion = RetrofitController
                .getRegionControllerApi()
                .getRegionById(headers, regionId)
                .execute();
        assertThat(callGetAfterDeleteRegion.code()).isEqualTo(SC_NOT_FOUND);
    }
}