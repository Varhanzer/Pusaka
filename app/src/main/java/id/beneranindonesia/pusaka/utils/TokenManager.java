package id.beneranindonesia.pusaka.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import id.beneranindonesia.pusaka.api.GetDataService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.api.Token_1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenManager {

    private static volatile TokenManager instance;

    private final String KEY_TOKEN = "TOKEN";
    private final String KEY_PREFERENCES_TOKEN = "USER.TOKEN";

    public Listener listener;

    public static String ACCESS_TOKEN = "";

    public static TokenManager getInstance() {
        if (instance == null) {
            synchronized (Session.class) { if (instance == null) instance = new TokenManager(); }
        }
        return instance;
    }

    public void getToken(final Context context) {
        GetDataService service = RetrofitClientInstance.getInstance().create(GetDataService.class);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("grant_type", "password");

        Call<Token_1> call = service.getToken(hashMap);

        call.enqueue(new Callback<Token_1>() {
            @Override
            public void onResponse(Call<Token_1> call, Response<Token_1> response) {
                if(response.code() == 200 && response.body() != null) {
                    try {
                        JSONObject json = new JSONObject();
                        json.put("access_token", response.body().getAccess_token());
                        json.put("expires", response.body().getExpires_in());
                        json.put("token_type", response.body().getToken_type());

                        TokenManager.ACCESS_TOKEN = response.body().getAccess_token();
                        saveToken(json.toString(), context);
                        listener.getTokenSuccess(response.body());
                    } catch (NullPointerException | JSONException e) {
                        listener.getTokenFailed(0, e.getLocalizedMessage());
                    }
                } else {
                    listener.getTokenFailed(response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<Token_1> call, Throwable t) {
                Log.d("TOKENMANAGER", t.getMessage());
            }
        });
    }

    private void saveToken(String token, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES_TOKEN, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public interface Listener {
        void getTokenSuccess(Token_1 token);
        void getTokenFailed(int errorCode, String message);
    }

}



























