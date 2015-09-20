package id.sikerang.mobile.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class CommonPaging extends CommonResponse {
    @SerializedName("size")
    int size;

    public int getSize() {
        return size;
    }
}