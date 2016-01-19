package id.sikerang.mobile.pantautrend;

import android.os.Parcel;
import android.os.Parcelable;

import id.sikerang.mobile.models.CommonResponse;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrend extends CommonResponse implements Parcelable {
    public static final Creator<PantauTrend> CREATOR = new Creator<PantauTrend>() {
        @Override
        public PantauTrend createFromParcel(Parcel in) {
            return new PantauTrend(in);
        }

        @Override
        public PantauTrend[] newArray(int size) {
            return new PantauTrend[size];
        }
    };

    private String likes;
    private String date;

    public PantauTrend(Parcel in) {
        likes = in.readString();
        date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(likes);
        dest.writeString(date);
    }

    public String getLikes() {
        return likes;
    }

    public String getDate() {
        return date;
    }
}