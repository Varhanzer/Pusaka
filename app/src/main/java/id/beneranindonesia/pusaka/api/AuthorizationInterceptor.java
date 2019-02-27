package id.beneranindonesia.pusaka.api;

import java.io.IOException;
import java.util.HashMap;

import id.beneranindonesia.pusaka.utils.TokenManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class AuthorizationInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request.Builder newBuilder = originalRequest
                .newBuilder()
                .header("Authorization", "bearer " + TokenManager.ACCESS_TOKEN)
                .method(originalRequest.method(), originalRequest.body());
        Request newRequest = newBuilder.build();
        Response mainResponse = chain.proceed(newRequest);

        if (mainResponse.code() == 401 || mainResponse.code() == 403) {
            ApiService service = RetrofitClientInstance.getInstance().create(ApiService.class);

            HashMap<String, String> header = new HashMap<>();
            header.put("grant_type", "password");

            Call<Token_1> call = service.getToken(header);

            retrofit2.Response<Token_1> tokenResponse = call.execute();

            if (tokenResponse.code() == 200) {

                Token_1 token = tokenResponse.body();
                if(token != null) {

                    TokenManager.ACCESS_TOKEN = token.getAccess_token();
                    Request.Builder builder = newRequest
                            .newBuilder()
                            .header("Authorization", "bearer " + token.getAccess_token())
                            .method(newRequest.method(), newRequest.body());
                    mainResponse = chain.proceed(builder.build());

                }
            }
        }

        return mainResponse;
    }

}













