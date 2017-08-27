package hackathon.com.pagandopave.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Promotion implements Parcelable {

    String bannerUrl;
    String partnerLogoUrl;
    String partnerName;
    String title;
    String description;
    int pessoas;
    String dataInicio;
    String dataFim;
    String horaInicio;
    String horaFim;
    String valorOriginal;
    String valorFinal;

    public Promotion(JSONObject jsonObject) throws JSONException {

        JSONObject jsonPartner = jsonObject.getJSONObject("parceiro");
        partnerLogoUrl = jsonPartner.getString("logo");
        partnerName = jsonPartner.getString("razaosocial");
        bannerUrl = jsonObject.getString("fotopath");
        title = jsonObject.getString("titulo");
        description = jsonObject.getString("descricao");
        dataFim = jsonObject.getString("data_fim");
        dataInicio = jsonObject.getString("data_inicio");
        horaFim = jsonObject.getString("hora_fim");
        horaInicio = jsonObject.getString("hora_inicio");
        pessoas = jsonObject.getInt("pessoas");
        valorOriginal = jsonObject.getString("valor_original");
        valorFinal = jsonObject.getString("valor_final");
    }


    protected Promotion(Parcel in) {
        bannerUrl = in.readString();
        partnerLogoUrl = in.readString();
        partnerName = in.readString();
        title = in.readString();
        description = in.readString();
        pessoas = in.readInt();
        dataInicio = in.readString();
        dataFim = in.readString();
        horaInicio = in.readString();
        horaFim = in.readString();
        valorOriginal = in.readString();
        valorFinal = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bannerUrl);
        dest.writeString(partnerLogoUrl);
        dest.writeString(partnerName);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(pessoas);
        dest.writeString(dataInicio);
        dest.writeString(dataFim);
        dest.writeString(horaInicio);
        dest.writeString(horaFim);
        dest.writeString(valorOriginal);
        dest.writeString(valorFinal);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(String valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public String getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
    }

    public int getPessoas() {
        return pessoas;
    }

    public void setPessoas(int pessoas) {
        this.pessoas = pessoas;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
}
