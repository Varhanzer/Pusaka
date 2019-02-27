package id.beneranindonesia.pusaka.api;

import java.util.concurrent.TimeUnit;

import id.beneranindonesia.pusaka.constant.NetworkConstant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    public static Retrofit getInstance() {

        if (retrofit == null)
            retrofit = provideRetrofit(NetworkConstant.SERVER);

        return retrofit;
    }

    private static Retrofit provideRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);

        okHttpClientBuilder.addInterceptor(new AuthorizationInterceptor());
        return okHttpClientBuilder.build();
    }
}





























