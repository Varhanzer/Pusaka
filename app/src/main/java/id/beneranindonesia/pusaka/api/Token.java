package id.beneranindonesia.pusaka.api;

import android.util.Base64;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Token {

    public interface TokenListener {
        void tokenReceived(String access_token, String token_Type, String sid, String expires_in);
        void tokenDidFail(int errorCode, String message);
    }

    private static String ACCESS_TOKEN = "";
    private static String TOKEN_TYPE   = "";
    private static String SID          = "";
    private static String EXPIRES_IN   = "";

    public TokenListener listener;

    public void get() {
        if (!ACCESS_TOKEN.isEmpty() && !TOKEN_TYPE.isEmpty() && !SID.isEmpty() && !EXPIRES_IN.isEmpty()) {
            listener.tokenReceived(ACCESS_TOKEN, TOKEN_TYPE, SID, EXPIRES_IN);
            return;
        }
        AndroidNetworking.post(URLs.TOKEN_URL).addBodyParameter("grant_type", "password")
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ACCESS_TOKEN = response.getString("access_token");
                            TOKEN_TYPE   = response.getString("token_type");
                            EXPIRES_IN   = response.getString("expires_in");

                            String[] accessTokens = response.getString("access_token").split("\\.");
                            if (accessTokens.length > 1) {
                                int flags = Base64.NO_WRAP | Base64.URL_SAFE;
                                byte[] base64Decoded = Base64.decode(accessTokens[1], flags);
                                String base64DecodedString = new String(base64Decoded, "UTF-8");
                                try {
                                    JSONObject json = new JSONObject(base64DecodedString);
                                    SID = json.getString("sid");
                                    listener.tokenReceived(ACCESS_TOKEN, TOKEN_TYPE, SID, EXPIRES_IN);
                                } catch (JSONException e) {
                                    ACCESS_TOKEN = "";
                                    TOKEN_TYPE   = "";
                                    EXPIRES_IN   = "";
                                    SID          = "";
                                    listener.tokenDidFail(0, "Invalid access token");
                                }
                            } else {
                                ACCESS_TOKEN = "";
                                TOKEN_TYPE   = "";
                                EXPIRES_IN   = "";
                                SID          = "";
                                listener.tokenDidFail(0, "Invalid access token");
                            }
                        } catch (JSONException | UnsupportedEncodingException e) {
                            ACCESS_TOKEN = "";
                            TOKEN_TYPE   = "";
                            EXPIRES_IN   = "";
                            SID          = "";
                            listener.tokenDidFail(0, "Failed to get value from token json");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.tokenDidFail(anError.getErrorCode(), anError.getMessage());
                    }
                });
    }

}
