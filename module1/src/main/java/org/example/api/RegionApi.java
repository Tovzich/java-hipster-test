package org.example.api;

import org.example.client.RetrofitClient;
import org.example.model.RegionModelRequest;
import org.example.model.RegionModelResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface RegionApi extends RetrofitClient.Api {
    @GET("api/regions/{id}")
    Call<RegionModelResponse> getRegionById(@HeaderMap Map<String, String> headers, @Path("id") Integer id);

    @GET("api/regions")
    Call<List<RegionModelResponse>> getRegions(@HeaderMap Map<String, String> headers);

    @POST("api/regions")
    Call<RegionModelResponse> postRegion(@HeaderMap Map<String, String> headers,
                                         @Body RegionModelRequest regionModelRequest);

    @DELETE("api/regions/{id}")
    Call<RegionModelResponse> deleteRegionById(@HeaderMap Map<String, String> headers, @Path("id") Integer id);
}