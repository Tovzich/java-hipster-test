package org.example.api;

import org.example.client.RetrofitClient;
import org.example.model.RegionModelRequest;
import org.example.model.RegionModelResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface RegionApi extends RetrofitClient.Api {
    @GET("api/regions/{id}")
    Call<RegionModelResponse> getRegionById(@Path("id") Integer id);

    @POST("api/regions")
    Call<RegionModelResponse> postRegion(@Body RegionModelRequest regionModelRequest);

    @DELETE("api/regions/{id}")
    Call<RegionModelResponse> deleteRegionById(@Path("id") Integer id);
}