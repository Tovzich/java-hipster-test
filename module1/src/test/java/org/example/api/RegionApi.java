package org.example.api;

import org.example.model.RegionModel;
import org.example.model.TokenModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RegionApi {
    @GET("api/regions/{id}")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYzNTM0NTg5MX0.EEjxm3AEwpDhdnmKqr2ZuidvGBf3aR2OdYki3jcNkkNHBpHn7c6FIHuFz_50JUZXsM_3o91Ze6eXN6B6DwISCw")
    Call<RegionModel> getRegionById(@Path("id") Integer id);

    @GET("api/regions")
    Call<List<RegionModel>> getRegions();

    @POST("api/regions")
    //@Headers("Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYzNTM0NTg5MX0.EEjxm3AEwpDhdnmKqr2ZuidvGBf3aR2OdYki3jcNkkNHBpHn7c6FIHuFz_50JUZXsM_3o91Ze6eXN6B6DwISCw")
    Call<RegionModel> postRegion(@Body RegionModel regionModel);

    @POST("api/authenticate")
    Call<TokenModel> postToken(@Body TokenModel tokenModel);
}