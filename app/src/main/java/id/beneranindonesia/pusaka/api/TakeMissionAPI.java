package id.beneranindonesia.pusaka.api;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import id.beneranindonesia.pusaka.models.ContentList;
import id.beneranindonesia.pusaka.utils.Session;

public class TakeMissionAPI implements Token.TokenListener {

    public interface Listener {
        void takeSuccess();
        void takeFailed(int statusCode, String message);
    }

    public Listener listener;
    private HashMap<String, String> params;
    private String TAG = "TakeMissionAPI";

    public void take(String missionID) {
        try {
            JSONObject json = new JSONObject();
            json.put("lang", "id");
            json.put("missionID", missionID);
            json.put("userID", Session.getInstance().getUserID());

            HashMap<String, String > params = new HashMap<>();
            params.put("pikachu", json.toString());

            this.params  = params;
            Token token = new Token();
            token.listener = this;
            token.get();
        } catch (JSONException e) { listener.takeFailed(0, "Failed to create JSONObject"); }
    }

    @Override
    public void tokenReceived(String access_token, String token_Type, String sid, String expires_in) {
        AndroidNetworking.post(URLs.CONTENT_TAKE_MISSION)
                .addHeaders("Authorization", "bearer " + access_token)
                .addBodyParameter(params)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        listener.takeSuccess();
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "" + anError.getErrorCode());
                        Log.d(TAG,anError.getErrorDetail());
                        listener.takeFailed(anError.getErrorCode(), anError.getMessage());
                    }
                });
    }

    @Override
    public void tokenDidFail(int errorCode, String message) {
        listener.takeFailed(errorCode, message);
    }

}


































