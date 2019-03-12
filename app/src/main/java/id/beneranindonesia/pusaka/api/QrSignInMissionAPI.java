package id.beneranindonesia.pusaka.api;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class QrSignInMissionAPI implements Token.TokenListener {


    public interface QrSignInAPiListener {
        void QrSignInSuccess(String qrString);
        void QrSignInFailed(int errorCode, String message);
    }

    private HashMap<String, String> params;
    private Token token;
    public QrSignInAPiListener listener;

    public void register(HashMap<String, String> params) {
        this.params = params;
        token = new Token();
        token.listener = this;
        token.get();

    }

    @Override
    public void tokenReceived(String access_token, String token_Type, String sid, String expires_in) {
        AndroidNetworking.post(URLs.ONGOING_MISSION_SIGNIN)
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
                            String qrString = json.getString("qrString");
                            listener.QrSignInSuccess(qrString);
                        } catch (JSONException e) {
                            listener.QrSignInFailed(0, "Failed to decode json");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        listener.QrSignInFailed(anError.getErrorCode(), anError.getMessage());
                    }
                });
    }

    @Override
    public void tokenDidFail(int errorCode, String message) {
        listener.QrSignInFailed(errorCode, message);
    }

}























































