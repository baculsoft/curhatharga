package id.sikerang.mobile.kawalperubahan;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IKawalPerubahanService {
    @GET("/kawal-perubahan")
    void readKawalPerubahan(Callback<KawalPerubahan> pKawalPerubahanCallback);
}