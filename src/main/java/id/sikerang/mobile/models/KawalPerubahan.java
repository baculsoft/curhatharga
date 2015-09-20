package id.sikerang.mobile.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahan extends CommonPaging {
    @SerializedName("contents")
    List<KawalPerubahanContents> kawalPerubahanContents;

    public List<KawalPerubahanContents> getKawalPerubahanContents() {
        return kawalPerubahanContents;
    }
}