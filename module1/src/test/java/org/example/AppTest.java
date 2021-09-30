package org.example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.example.api.CountryApi;
import org.example.api.RegionApi;
import org.example.model.CountryModel;
import org.example.model.RegionModel;
import org.example.model.TokenModel;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    Retrofit retrofit;
    Map<String, String> headers = new HashMap<>();

    @BeforeEach
    void setUp() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://31.131.249.140:8080/")
                .client(httpClient.build())
                .build();

        RegionApi regionApi = retrofit.create(RegionApi.class);
        TokenModel tokenModel = new TokenModel();
        tokenModel.setUsername("admin");
        tokenModel.setRememberMe(true);
        tokenModel.setPassword("u7ljdajLNo7PsVw7");

        try {
            headers.put("Authorization", "Bearer " + regionApi.postToken(tokenModel).execute().body().getIdToken());
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Ошибка получения токена", e);
        }
    }

    @Test
    void shouldGetToken() throws IOException {
        RegionApi regionApi = retrofit.create(RegionApi.class);

        TokenModel tokenModel = new TokenModel();
        tokenModel.setUsername("admin");
        tokenModel.setRememberMe(true);
        tokenModel.setPassword("u7ljdajLNo7PsVw7");

        Response<TokenModel> call = regionApi.postToken(tokenModel).execute();
        assertThat(call.code()).isEqualTo(SC_OK);
    }

    @Test
    void shouldPostRegion() throws IOException {
        RegionApi regionApi = retrofit.create(RegionApi.class);

        RegionModel regionModel = new RegionModel();
        regionModel.setRegionName("Скайрим_" + (int)(Math.random() * 1000));

        Response<RegionModel> callPost = regionApi.postRegion(headers, regionModel).execute();
        assertThat(callPost.code()).isEqualTo(SC_CREATED);

        final Integer id = callPost.body().getId();

        Response<RegionModel> callGetAfterPost = regionApi
                .getRegionById(headers, id).execute();
        SoftAssertions.assertSoftly(softly -> assertThat(callGetAfterPost.code()).isEqualTo(SC_OK));

        Response<RegionModel> callDelete = regionApi
                .deleteRegionById(headers, id).execute();
        assertThat(callDelete.code()).isEqualTo(SC_NO_CONTENT);

        Response<RegionModel> callGetAfterDelete = regionApi
                .getRegionById(headers, id).execute();
        assertThat(callGetAfterDelete.code()).isEqualTo(SC_NOT_FOUND);
    }

    @Test
    void shouldPostCountry() throws IOException {
        RegionApi regionApi = retrofit.create(RegionApi.class);

        RegionModel regionModel = new RegionModel();
        regionModel.setRegionName("Скайрим_" + (int)(Math.random() * 1000));

        Response<RegionModel> callPostRegion = regionApi.postRegion(headers, regionModel).execute();
        assertThat(callPostRegion.code()).isEqualTo(SC_CREATED);

        CountryApi countryApi = retrofit.create(CountryApi.class);

        CountryModel countryModel = new CountryModel();
        countryModel.setCountryName("Tamriel_" + (int) (Math.random() * 1000));
        countryModel.setRegionId(callPostRegion.body().getId().toString());
        System.out.println(callPostRegion.body().getId());
        countryModel.setRegion(callPostRegion.body());

        Response<CountryModel> callPostCountry = countryApi.postCountry(headers, countryModel).execute();
        assertThat(callPostCountry.code()).isEqualTo(SC_CREATED);

        final Integer id = callPostCountry.body().getId();

        Response<CountryModel> callGetAfterPost = countryApi
                .getCountryById(headers, id).execute();
        assertThat(callGetAfterPost.code()).isEqualTo(SC_OK);

        Response<CountryModel> callDelete = countryApi
                .deleteCountryById(headers, id).execute();
        assertThat(callDelete.code()).isEqualTo(SC_NO_CONTENT);

        Response<CountryModel> callGetAfterDelete = countryApi
                .getCountryById(headers, id).execute();
        assertThat(callGetAfterDelete.code()).isEqualTo(SC_NOT_FOUND);
    }
}