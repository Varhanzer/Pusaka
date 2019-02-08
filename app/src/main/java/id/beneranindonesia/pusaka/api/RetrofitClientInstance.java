package id.beneranindonesia.pusaka.api;

import java.util.List;

import id.beneranindonesia.pusaka.constant.NetworkConstant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(NetworkConstant.SERVER).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}





























