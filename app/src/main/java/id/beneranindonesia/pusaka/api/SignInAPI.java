package id.beneranindonesia.pusaka.api;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import id.beneranindonesia.pusaka.utils.Session;

public class SignInAPI implements Token.TokenListener {

    public interface Listener {
        void signInSuccess();
        void signInFailed(int statusCode, String message);
    }

    private Token token;
    private Context context;
    private HashMap<String, String> params;

    public Listener listener;

    public void signIn(Context context, String email, String password) {
        try {
            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);

            HashMap<String, String > params = new HashMap<>();
            params.put("pikachu", json.toString());
            this.params  = params;
            this.context = context;

            token = new Token();
            token.listener = this;
            token.get();
        } catch (JSONException e) { listener.signInFailed(0, "General error"); }

    }

    @Override
    public void tokenReceived(String access_token, String token_Type, String sid, String expires_in) {
        AndroidNetworking.post(URLs.SIGN_IN_URL)
                .addHeaders("Authorization", "bearer " + access_token)
                .addBodyParameter(params)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String userID    = json.getString("userID");
                            String imageFile = json.getString("imageFile");
                            String nickName  = json.getString("nickName");
                            Session.getInstance().saveUser(context, userID, nickName, imageFile);
                            listener.signInSuccess();
                        } catch (JSONException e) {
                            listener.signInFailed(0, "Failed to convert response to JSON");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        System.out.println(anError.getErrorCode());
                        System.out.println(anError.getMessage());
                    }
                });
    }

    @Override
    public void tokenDidFail(int errorCode, String message) {
        listener.signInFailed(errorCode, message);
    }
}
























