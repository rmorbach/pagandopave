package hackathon.com.pagandopave.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Extract implements Parcelable {

    private String extractIcon;
    private float extractValue;
    private String extractCategory;
    private String extractPlace;
    private String extractDate;
    private boolean charge;

    public Extract(JSONObject jsonObject) throws JSONException {
        extractValue = Float.parseFloat(jsonObject.getString("valor"));
        charge = jsonObject.getString("tipo").equals("CARGA");
        extractPlace = jsonObject.getString("estabelecimento");
        extractDate = jsonObject.getString("dataHora");
    }

    protected Extract(Parcel in) {
        extractIcon = in.readString();
        extractValue = in.readFloat();
        extractCategory = in.readString();
        extractPlace = in.readString();
        extractDate = in.readString();
        charge = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(extractIcon);
        dest.writeFloat(extractValue);
        dest.writeString(extractCategory);
        dest.writeString(extractPlace);
        dest.writeString(extractDate);
        dest.writeByte((byte) (charge ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Extract> CREATOR = new Creator<Extract>() {
        @Override
        public Extract createFromParcel(Parcel in) {
            return new Extract(in);
        }

        @Override
        public Extract[] newArray(int size) {
            return new Extract[size];
        }
    };

    public String getExtractIcon() {
        return extractIcon;
    }

    public void setExtractIcon(String extractIcon) {
        this.extractIcon = extractIcon;
    }

    public float getExtractValue() {
        return extractValue;
    }

    public void setExtractValue(float extractValue) {
        this.extractValue = extractValue;
    }

    public String getExtractCategory() {
        return extractCategory;
    }

    public void setExtractCategory(String extractCategory) {
        this.extractCategory = extractCategory;
    }

    public String getExtractPlace() {
        return extractPlace;
    }

    public void setExtractPlace(String extractPlace) {
        this.extractPlace = extractPlace;
    }

    public String getExtractDate() {
        return extractDate;
    }

    public void setExtractDate(String extractDate) {
        this.extractDate = extractDate;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }
}
