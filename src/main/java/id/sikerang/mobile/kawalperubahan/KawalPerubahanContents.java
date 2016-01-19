package id.sikerang.mobile.kawalperubahan;

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

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}