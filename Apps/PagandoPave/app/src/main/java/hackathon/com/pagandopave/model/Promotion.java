package hackathon.com.pagandopave.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Promotion implements Parcelable {

    String id;
    String bannerUrl;
    String partnerLogoUrl;
    String title;
    String description;

    protected Promotion(Parcel in) {
        id = in.readString();
        bannerUrl = in.readString();
        partnerLogoUrl = in.readString();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Promotion> CREATOR = new Creator<Promotion>() {
        @Override
        public Promotion createFromParcel(Parcel in) {
            return new Promotion(in);
        }

        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getPartnerLogoUrl() {
        return partnerLogoUrl;
    }

    public void setPartnerLogoUrl(String partnerLogoUrl) {
        this.partnerLogoUrl = partnerLogoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(bannerUrl);
        parcel.writeString(partnerLogoUrl);
        parcel.writeString(title);
        parcel.writeString(description);
    }
}
