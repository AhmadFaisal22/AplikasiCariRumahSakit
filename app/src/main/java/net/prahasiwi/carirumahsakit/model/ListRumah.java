package net.prahasiwi.carirumahsakit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PRAHASIWI on 17/12/2017.
 */

public class ListRumah {
    @SerializedName("semarang")
    private List<Rumah> listRumahSemarang;

    public List<Rumah> getListRumahSemarang() {
        return listRumahSemarang;
    }
}
