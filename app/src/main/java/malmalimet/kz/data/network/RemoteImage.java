package malmalimet.kz.data.network;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * An image received from the server. Contains two URLs, one for the thumbnail, and
 * one for the full image, as well as the image ID.
 */
public class RemoteImage implements Parcelable, Serializable {

    private static final long serialVersionUID = -1130829443484976267L;

    public Integer id;
    public String thumbnailUrl;
    public String mainUrl;
    public boolean hasFullUrl = false;

    public RemoteImage() {
    }

    public RemoteImage(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public RemoteImage(String mainUrl, boolean hasFullUrl) {
        this.mainUrl = mainUrl;
        this.hasFullUrl = hasFullUrl;
    }

    protected RemoteImage(Parcel in) {
        id = in.readInt();
        thumbnailUrl = in.readString();
        mainUrl = in.readString();
        hasFullUrl = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id != null) {
            dest.writeInt(id);
        } else {
            dest.writeInt(0);
        }
        dest.writeString(thumbnailUrl);
        dest.writeString(mainUrl);
        dest.writeByte((byte) (hasFullUrl ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RemoteImage> CREATOR = new Creator<RemoteImage>() {
        @Override
        public RemoteImage createFromParcel(Parcel in) {
            return new RemoteImage(in);
        }

        @Override
        public RemoteImage[] newArray(int size) {
            return new RemoteImage[size];
        }
    };
}
