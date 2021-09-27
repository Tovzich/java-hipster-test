package org.example;

import org.example.api.RegionApi;
import org.example.model.RegionModel;
import org.example.model.TokenModel;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    Retrofit retrofit;
    String token;

    @BeforeEach
    void setUp() throws IOException {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://31.131.249.140:8080/")
                .build();

        RegionApi regionApi = retrofit.create(RegionApi.class);
        TokenModel tokenModel = new TokenModel();
        tokenModel.setUsername("admin");
        tokenModel.setRememberMe(true);
        tokenModel.setPassword("u7ljdajLNo7PsVw7");
        token = regionApi.postToken(tokenModel).execute().body().getIdToken();
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

        token = call.body().getIdToken();
    }

    @Test
    void shouldPostRegion() throws IOException {
        RegionApi regionApi = retrofit.create(RegionApi.class);

        RegionModel regionModel = new RegionModel();
        regionModel.setRegionName("Скайрим");

        Response<RegionModel> call = regionApi.postRegion(regionModel).execute();
        System.out.println(call.body());
        assertThat(call.code()).isEqualTo(SC_CREATED);
    }

    @Test
    void shouldGetRegionByID() throws IOException {
        RegionApi regionApi = retrofit.create(RegionApi.class);
        Response<RegionModel> call = regionApi.getRegionById(1051).execute();
        assertThat(call.code()).isEqualTo(SC_OK);
    }
}