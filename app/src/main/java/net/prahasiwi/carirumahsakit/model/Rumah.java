package net.prahasiwi.carirumahsakit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PRAHASIWI on 17/12/2017.
 */

public class Rumah {
    @SerializedName("id_rs")
    private String idRumah;

    @SerializedName("nama_rs")
    private String namaRumah;

    @SerializedName("gambar_rs")
    private String gambarRumah;

    @SerializedName("lokasi_rs")
    private String alamatRumah;

    @SerializedName("telepon_rs")
    private String teleponRumah;

    @SerializedName("email_rs")
    private String emailRumah;

    @SerializedName("web_rs")
    private String webRumah;

    @SerializedName("latitude_rs")
    private String latRumah;

    @SerializedName("longitude_rs")
    private String longRumah;

    public Rumah(String idRumah, String namaRumah, String gambarRumah, String alamatRumah, String teleponRumah, String emailRumah, String webRumah, String latRumah, String longRumah) {
        this.idRumah = idRumah;
        this.namaRumah = namaRumah;
        this.gambarRumah = gambarRumah;
        this.alamatRumah = alamatRumah;
        this.teleponRumah = teleponRumah;
        this.emailRumah = emailRumah;
        this.webRumah = webRumah;
        this.latRumah = latRumah;
        this.longRumah = longRumah;
    }

    public String getIdRumah() {
        return idRumah;
    }

    public String getNamaRumah() {
        return namaRumah;
    }

    public String getGambarRumah() {
        return gambarRumah;
    }

    public String getTeleponRumah() {
        return teleponRumah;
    }

    public String getEmailRumah() {
        return emailRumah;
    }

    public String getWebRumah() {
        return webRumah;
    }

    public String getAlamatRumah() {
        return alamatRumah;
    }

    public String getLatRumah() {
        return latRumah;
    }

    public String getLongRumah() {
        return longRumah;
    }
}
