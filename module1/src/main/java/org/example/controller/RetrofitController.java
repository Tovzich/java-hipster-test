package org.example.controller;

import org.example.api.AuthApi;
import org.example.api.CountryApi;
import org.example.api.RegionApi;
import org.example.client.RetrofitClient;

public class RetrofitController {
    private static final RetrofitClient retrofit = new RetrofitClient();
    private static AuthApi authControllerApi;
    private static CountryApi countryControllerApi;
    private static RegionApi regionControllerApi;

    private RetrofitController() {
    }

    public static AuthApi getAuthControllerApi() {
        if (authControllerApi == null) {
            authControllerApi = retrofit.buildClient(AuthApi.class);
        }
        return authControllerApi;
    }

    public static CountryApi getCountryControllerApi() {
        if (countryControllerApi == null) {
            countryControllerApi = retrofit.buildClient(CountryApi.class);
        }
        return countryControllerApi;
    }

    public static RegionApi getRegionControllerApi() {
        if (regionControllerApi == null) {
            regionControllerApi = retrofit.buildClient(RegionApi.class);
        }
        return regionControllerApi;
    }
}