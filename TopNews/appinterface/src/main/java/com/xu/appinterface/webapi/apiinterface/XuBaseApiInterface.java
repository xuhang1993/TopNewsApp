package com.xu.appinterface.webapi.apiinterface;

import com.xu.appinterface.webapi.apimodel.XuBaseApiModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 16413 on 2017/4/26.
 */
public interface XuBaseApiInterface {

   //Retrofit
   //query
   @GET("/Course/GetUserCreatedCourseList")
   Observable<List<XuBaseApiModel>> getApiModel(@Query("ssotoken") String token);
   //path
   @GET("/{path}/GetUserCreatedCourseList")
   Call<List<XuBaseApiModel>> getApiModelPath(@Path("path") String path, @Query("ssotoken") String token);
   //querymap
   @GET("/Course/GetUserCreatedCourseList")
   Call<List<XuBaseApiModel>> getApiModelQueryMap(@QueryMap Map<String,String> map);
   //Post
   @POST("/Course/GetUserCreatedCourseList")
   Call<List<XuBaseApiModel>> getApiModelPost(@Body RequestBody requestBody);
   //Multipart
   @Multipart
   Call<String> getApiModelMultipart(@Part("file") RequestBody photo);

   //FormUrlEncoded
   @FormUrlEncoded()
   @POST("/Course/GetUserCreatedCourseList")
   Call<String> getApiModelFormUrlEncoded(@Field("userName") String userName,@Field("passWord") String passWord);


}
