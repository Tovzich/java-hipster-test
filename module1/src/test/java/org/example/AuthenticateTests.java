package org.example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.assertj.core.api.SoftAssertions;
import org.example.api.AuthApi;
import org.example.model.AuthRequest;
import org.example.model.AuthResponse;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class AuthenticateTests {
    @Test
    void shouldGetToken() throws IOException {
        AuthRequest authRequest = new AuthRequest()
                .password("u7ljdajLNo7PsVw7")
                .username("admin")
                .rememberMe(true);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://31.131.249.140:8080/")
                .client(httpClient.build())
                .build();
        AuthApi authApi = retrofit.create(AuthApi.class);
        Response<AuthResponse> call = authApi.authenticate(authRequest).execute();

        assertThat(call.code()).isEqualTo(SC_OK);
        assertThat(call.body()).isNotNull();

        SoftAssertions.assertSoftly(softly -> {
            assertThat(call.body().getIdToken()).isNotEmpty();
            assertThat(call.body().getAdditionalProperties()).isEmpty();
        });
    }
}