package org.example.api;

import org.example.client.RetrofitClient;
import org.example.model.AuthRequest;
import org.example.model.AuthResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi extends RetrofitClient.Api {
    @POST("api/authenticate")
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);
}