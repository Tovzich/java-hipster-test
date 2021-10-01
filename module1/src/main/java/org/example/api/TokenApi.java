package org.example.api;

import org.example.model.TokenModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TokenApi {
    @POST("api/authenticate")
    Call<TokenModel> postToken(@Body TokenModel tokenModel);
}
