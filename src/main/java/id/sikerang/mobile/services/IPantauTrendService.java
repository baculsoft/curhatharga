package id.sikerang.mobile.services;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IPantauTrendService {
    @GET("/trend/{komoditas}")
    void readPantauTrend(@Path("komoditas") String komoditas);
}
