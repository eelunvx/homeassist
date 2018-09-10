package com.axzae.homeassistant.provider;

import com.axzae.homeassistant.model.Entity;
import com.axzae.homeassistant.model.LogSheet;
import com.axzae.homeassistant.model.rest.BootstrapResponse;
import com.axzae.homeassistant.model.rest.CallServiceRequest;
import com.axzae.homeassistant.model.rest.FCMAndroidRequest;
import com.axzae.homeassistant.model.rest.FCMAndroidResponse;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RestAPIService {

    @GET("/api/bootstrap")
    Call<BootstrapResponse> bootstrap(@Header("x-ha-access") String password);

    @GET("/api/bootstrap")
    Call<String> rawBootstrap(@Header("x-ha-access") String password);

    @GET("/api/states")
    Call<String> rawStates(@Header("x-ha-access") String password);

    @GET("/api/states")
    Call<ArrayList<Entity>> getStates(@Header("x-ha-access") String password);

    @GET("/api/states/{entityId}")
    Call<Entity> getState(@Header("x-ha-access") String password, @Path("entityId") String entityId);

    @POST("/api/services/{domain}/{service}")
    Call<ArrayList<Entity>> callService(@Header("x-ha-access") String password, @Path("domain") String domain, @Path("service") String service, @Body CallServiceRequest json);

    @GET("/api/history/period")
    Call<ArrayList<ArrayList<Entity>>> getHistory(@Header("x-ha-access") String password, @Query("filter_entity_id") String entityId);

    @GET("/api/logbook/{timestamp}")
    Call<ArrayList<LogSheet>> getLogbook(@Header("x-ha-access") String password, @Path("timestamp") String domain);

    @POST("/api/notify.fcm-android")
    Call<FCMAndroidResponse> postToken(@Header("x-ha-access") String password, @Body FCMAndroidRequest token);

    @POST("/api/notify.fcm-android/callback")
    Call<FCMAndroidResponse> postFCMCallback(@Header("x-ha-access") String password, @Body FCMAndroidRequest action);

    @HTTP(method = "DELETE", path = "/api/notify.fcm-android", hasBody = true)
    Call<FCMAndroidResponse> deleteToken(@Header("x-ha-access") String password, @Body FCMAndroidRequest token);

//    @GET("api/settings/all")
//    Call<RetrieveSettingsResponse> getSettings(
//            @Header("Authorization") String token,
//            @Query("appBuild") String appBuild,
//            @Query("appVersion") String appVersion,
//            @Query("lovVersion") String lovVersion,
//            @Query("imei") String imei,
//            @Query("model") String model,
//            @Query("deviceos") String deviceOs,
//            @Query("lat") String latitude,
//            @Query("long") String longitude
//    );

}
