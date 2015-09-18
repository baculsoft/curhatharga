package id.sikerang.mobile.services;

import id.sikerang.mobile.models.CommonResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IProductService {
    @FormUrlEncoded
    @POST("/api/save")
    void createProduct(@Field("latitude") String latitude,
                       @Field("longitude") String longitude,
                       @Field("screenName") String screenName,
                       @Field("productName") String productName,
                       @Field("likes") boolean likes,
                       @Field("text") String text,
                       Callback<CommonResponse> callback);
}