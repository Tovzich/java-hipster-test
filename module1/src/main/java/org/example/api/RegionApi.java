package org.example.api;

import org.example.model.RegionModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface RegionApi {
    @GET("api/regions/{id}")
    Call<RegionModel> getRegionById(@HeaderMap Map<String, String> headers, @Path("id") Integer id);

    @GET("api/regions")
    Call<List<RegionModel>> getRegions(@HeaderMap Map<String, String> headers);

    @POST("api/regions")
    Call<RegionModel> postRegion(@HeaderMap Map<String, String> headers, @Body RegionModel regionModel);

    @DELETE("api/regions/{id}")
    Call<RegionModel> deleteRegionById(@HeaderMap Map<String, String> headers, @Path("id") Integer id);
}