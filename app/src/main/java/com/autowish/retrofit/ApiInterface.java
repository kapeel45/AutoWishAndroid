package com.autowish.retrofit;

import com.google.gson.JsonElement;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("user")
    Call<JsonElement> getAllUsers();

    @FormUrlEncoded
    @POST("user/add")
    Call<JsonElement> addPurchase(@Field("firstName") String firstName,
                                  @Field("lastName") String lastName,
                                  @Field("mobile") String mobile,
                                  @Field("email") String email,
                                  @Field("currentArea") String currentArea,
                                  @Field("lattitude") String lattitude,
                                  @Field("longitude") String longitude);

}




