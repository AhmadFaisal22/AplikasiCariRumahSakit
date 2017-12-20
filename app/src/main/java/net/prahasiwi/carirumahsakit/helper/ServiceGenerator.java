package net.prahasiwi.carirumahsakit.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PRAHASIWI on 17/12/2017.
 */

public class ServiceGenerator {
    private static final String BASE_URL = "https://script.google.com/macros/s/AKfycby64KySQQX-nMHdVXMAafYOK09E-xbxo_lQHFiVp-pJJawFOJo/exec/";
    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
