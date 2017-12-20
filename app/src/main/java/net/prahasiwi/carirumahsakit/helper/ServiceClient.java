package net.prahasiwi.carirumahsakit.helper;

import net.prahasiwi.carirumahsakit.model.ListRumah;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PRAHASIWI on 17/12/2017.
 */

public interface ServiceClient {
    @GET("exec")
    Call<ListRumah> getRumah(@Query("sheet") String namaSheet);
}
