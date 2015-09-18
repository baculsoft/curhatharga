package id.sikerang.mobile.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class CommonResponse {
    @SerializedName("code")
    int code;

    @SerializedName("status")
    String status;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}