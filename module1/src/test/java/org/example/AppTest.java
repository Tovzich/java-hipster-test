package org.example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.api.RegionApi;
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
        regionModel.setRegionName("Скайрим_" + (int)(Math.random() * 100));

        Response<RegionModel> call = regionApi.postRegion(headers, regionModel).execute();
        System.out.println(call.body());
        assertThat(call.code()).isEqualTo(SC_CREATED);
    }

    @Test
    void shouldGetRegionByID() throws IOException {
        RegionApi regionApi = retrofit.create(RegionApi.class);
        Response<RegionModel> call = regionApi.getRegionById(headers, 1051).execute();
        assertThat(call.code()).isEqualTo(SC_OK);
    }
}