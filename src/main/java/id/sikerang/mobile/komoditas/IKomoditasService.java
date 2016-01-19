package id.sikerang.mobile.komoditas;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IKomoditasService {
    @FormUrlEncoded
    @POST("/save")
    void createKomoditas(@Field("latitude") String pLatitude,
                         @Field("longitude") String pLongitude,
                         @Field("screenName") String pScreenName,
                         @Field("productName") String pProductName,
                         @Field("likes") boolean isLikes,
                         @Field("text") String pText,
                         Callback<Komoditas> pKomoditasCallback);
}