package id.sikerang.mobile.pantautrend;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.sikerang.mobile.models.CommonResponse;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrend extends CommonResponse {
    @SerializedName("contents")
    List<PantauTrendContents> pantauTrendContents;

    public List<PantauTrendContents> getPantauTrendContents() {
        return pantauTrendContents;
    }
}