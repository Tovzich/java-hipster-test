package org.example.api;

import org.example.model.CountryModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface CountryApi {
    @GET("api/countries/{id}")
    Call<CountryModel> getCountryById(@HeaderMap Map<String, String> headers, @Path("id") Integer id);

    @POST("api/countries")
    Call<CountryModel> postCountry(@HeaderMap Map<String, String> headers, @Body CountryModel countryModel);

    @DELETE("api/countries/{id}")
    Call<CountryModel> deleteCountryById(@HeaderMap Map<String, String> headers, @Path("id") Integer id);
}