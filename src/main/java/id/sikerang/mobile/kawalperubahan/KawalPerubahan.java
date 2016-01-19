package id.sikerang.mobile.kawalperubahan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.sikerang.mobile.models.CommonPaging;

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