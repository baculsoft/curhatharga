package id.sikerang.mobile.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class CommonResponse implements Parcelable {
    public static final Parcelable.Creator<CommonResponse> CREATOR = new Parcelable.Creator<CommonResponse>() {
        public CommonResponse createFromParcel(Parcel in) {
            return new CommonResponse(in);
        }

        public CommonResponse[] newArray(int size) {
            return new CommonResponse[size];
        }
    };

    public static final int STATUS_OK = 1;
    public static final int STATUS_FAIL = 0;

    private int statusCode;

    public CommonResponse() {
        super();
    }

    public CommonResponse(Parcel in) {
        statusCode = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(statusCode);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int pStatusCode) {
        statusCode = pStatusCode;
    }
}