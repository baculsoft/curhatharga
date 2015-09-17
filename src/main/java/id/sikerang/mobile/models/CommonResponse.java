package id.sikerang.mobile.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class CommonResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("status")
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}