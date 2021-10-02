package org.example.api;

import org.example.client.RetrofitClient;
import org.example.model.CountryModelRequest;
import org.example.model.CountryModelResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface CountryApi extends RetrofitClient.Api {
    @GET("api/countries/{id}")
    Call<CountryModelResponse> getCountryById(@HeaderMap Map<String, String> headers, @Path("id") Integer id);

    @POST("api/countries")
    Call<CountryModelResponse> postCountry(@HeaderMap Map<String, String> headers,
                                           @Body CountryModelRequest countryModelRequest);

    @DELETE("api/countries/{id}")
    Call<CountryModelResponse> deleteCountryById(@HeaderMap Map<String, String> headers, @Path("id") Integer id);
}