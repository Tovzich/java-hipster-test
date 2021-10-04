package org.example.auth;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.example.controller.RetrofitController;
import org.example.model.AuthRequest;
import org.example.model.AuthResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.UncheckedIOException;

public class BearerAuthenticator implements Authenticator {
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NotNull Response response) {
        AuthRequest authRequest = new AuthRequest()
                .password("u7ljdajLNo7PsVw7")
                .username("admin")
                .rememberMe(true);

        retrofit2.Response<AuthResponse> callPost;
        try {
            callPost = RetrofitController
                    .getAuthControllerApi()
                    .authenticate(authRequest)
                    .execute();
        } catch (IOException e) {
            throw new UncheckedIOException("I/O Ошибка получения токена", e);
        }

        assert callPost.body() != null;

        return response.request()
                .newBuilder()
                .header("Authorization", "Bearer " + callPost.body().getIdToken())
                .build();
    }
}