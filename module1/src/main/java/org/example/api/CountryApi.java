package org.example.api;

import org.example.client.RetrofitClient;
import org.example.model.CountryModelRequest;
import org.example.model.CountryModelResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface CountryApi extends RetrofitClient.Api {
    @GET("api/countries/{id}")
    Call<CountryModelResponse> getCountryById(@Path("id") Integer id);

    @POST("api/countries")
    Call<CountryModelResponse> postCountry(@Body CountryModelRequest countryModelRequest);

    @DELETE("api/countries/{id}")
    Call<CountryModelResponse> deleteCountryById(@Path("id") Integer id);
}