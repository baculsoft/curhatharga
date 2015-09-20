package id.sikerang.mobile.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanContents implements Parcelable {
    public static final Creator<KawalPerubahanContents> CREATOR = new Creator<KawalPerubahanContents>() {
        @Override
        public KawalPerubahanContents createFromParcel(Parcel in) {
            return new KawalPerubahanContents(in);
        }

        @Override
        public KawalPerubahanContents[] newArray(int size) {
            return new KawalPerubahanContents[size];
        }
    };

    private String content;
    private String title;
    private String date;

    public KawalPerubahanContents() {
        super();
    }

    public KawalPerubahanContents(Parcel in) {
        content = in.readString();
        title = in.readString();
        date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(content);
        dest.writeString(title);
        dest.writeString(date);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String pContent) {
        content = pContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String pDate) {
        date = pDate;
    }
}