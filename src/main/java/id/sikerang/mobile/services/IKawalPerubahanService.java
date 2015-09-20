package id.sikerang.mobile.services;

import id.sikerang.mobile.models.KawalPerubahan;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IKawalPerubahanService {
    @GET("/api/kawal-perubahan")
    void readKawalPerubahan(Callback<KawalPerubahan> callback);
}