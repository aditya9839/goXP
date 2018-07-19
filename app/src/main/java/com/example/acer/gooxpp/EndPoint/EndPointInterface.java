package com.example.acer.gooxpp.EndPoint;


import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by mohit on 24/07/17.
 */

public interface EndPointInterface {


//    //post method
//    @FormUrlEncoded
//    @POST("api/v1/device/token/authenticated")
//    Call<BaseModel> sendDeviceTokenAuthenticated(@FieldMap HashMap<String, String> params);
//
//
//
//    //post method with multipart
//    @Multipart
//    @POST("api/v1/oauth/android/newVideoMessage")
//    Call<BaseModel> uploadNewVideoMessage(@Part("title") RequestBody title,
//                                          @Part("description") RequestBody description,
//                                          @Part("placeId") RequestBody placeId,
//                                          @Part("latitude") RequestBody latitude,
//                                          @Part("longitude") RequestBody longitude,
//                                          @Part("locality") RequestBody locality,
//                                          @Part("onPriority") RequestBody onPriority,
//                                          @Part("category_id") RequestBody category_id,
//                                          @Part("is_private") RequestBody is_private,
//                                          @Part("person_name") RequestBody person_name,
//                                          @Part("is_self_user") RequestBody is_self_user,
//                                          @Part("type") RequestBody type, @Part MultipartBody.Part videoMessage);
//
//
//    //get method
//    @GET("api/v1/oauth/ios/idea/list/user")
//    Call<UserIdealistModelResult> getUserIdeaList();




}
