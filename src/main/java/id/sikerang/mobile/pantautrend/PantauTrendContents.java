package id.sikerang.mobile.pantautrend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class PantauTrendContents implements Parcelable {
    public static final Creator<PantauTrendContents> CREATOR = new Creator<PantauTrendContents>() {
        @Override
        public PantauTrendContents createFromParcel(Parcel in) {
            return new PantauTrendContents(in);
        }

        @Override
        public PantauTrendContents[] newArray(int size) {
            return new PantauTrendContents[size];
        }
    };

    private String likes;
    private String date;

    public PantauTrendContents(Parcel in) {
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
