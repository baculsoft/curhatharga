package id.sikerang.mobile.pantautrend;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IPantauTrendService {
    @GET("/trend/{komoditas}")
    void readPantauTrend(@Path("komoditas") String komoditas,
                         Callback<PantauTrend> callback);
}