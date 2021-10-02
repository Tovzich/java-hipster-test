package org.example;

import org.assertj.core.api.SoftAssertions;
import org.example.controller.RetrofitController;
import org.example.model.AuthRequest;
import org.example.model.AuthResponse;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class AuthenticateTests {
    @Test
    void shouldGetToken() throws IOException {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("admin");
        authRequest.setRememberMe(true);
        authRequest.setPassword("u7ljdajLNo7PsVw7");

        Response<AuthResponse> call = RetrofitController
                .getAuthControllerApi()
                .authenticate(authRequest)
                .execute();

        SoftAssertions.assertSoftly(softly -> {
            assertThat(call.code()).isEqualTo(SC_OK);
            assertThat(call.body().getIdToken()).isNotEmpty();
            assertThat(call.body().getAdditionalProperties()).isEmpty();
        });
    }
}