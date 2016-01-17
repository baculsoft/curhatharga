package id.sikerang.mobile.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class Komoditas extends CommonResponse implements Parcelable {
    public static final Parcelable.Creator<Komoditas> CREATOR = new Parcelable.Creator<Komoditas>() {
        public Komoditas createFromParcel(Parcel in) {
            return new Komoditas(in);
        }

        public Komoditas[] newArray(int size) {
            return new Komoditas[size];
        }
    };

    private String latitude;
    private String longitude;
    private String screenName;
    private String productName;
    private String text;
    private boolean likes;

    public Komoditas() {
        super();
    }

    public Komoditas(Parcel in) {
        latitude = in.readString();
        longitude = in.readString();
        screenName = in.readString();
        productName = in.readString();
        text = in.readString();
        likes = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(screenName);
        dest.writeString(productName);
        dest.writeString(text);
        dest.writeByte((byte) (likes ? 1 : 0));
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String pLatitude) {
        latitude = pLatitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String pLongitude) {
        longitude = pLongitude;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String pScreenName) {
        screenName = pScreenName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String pProductName) {
        productName = pProductName;
    }

    public String getText() {
        return text;
    }

    public void setText(String pText) {
        text = pText;
    }

    public boolean isLikes() {
        return likes;
    }

    public void setLikes(boolean pLikes) {
        likes = pLikes;
    }
}