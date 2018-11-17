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

import id.beneranindonesia.pusaka.models.ContentDetail;
import id.beneranindonesia.pusaka.models.ContentList;
import id.beneranindonesia.pusaka.utils.Session;

public class ContentDetailAPI implements Token.TokenListener {

    public interface Listener {
        void contentDetailReceived(ContentDetail missionDetail);
        void contentDetailDidFailWith(int statusCode, String message);
    }

    private HashMap<String, String> params;

    public Listener listener;

    public void get(String missionID) {
        try {
            JSONObject json = new JSONObject();
            json.put("lang", "id");
            json.put("userID", Session.getInstance().getUserID());
            json.put("missionID", missionID);

            HashMap<String, String > params = new HashMap<>();
            params.put("pikachu", json.toString());

            this.params  = params;
            Token token = new Token();
            token.listener = this;
            token.get();

        } catch (JSONException e) { listener.contentDetailDidFailWith(0, "Failed to create JSONObject"); }
    }

    @Override
    public void tokenReceived(String access_token, String token_Type, String sid, String expires_in) {
        AndroidNetworking.post(URLs.CONTENT_DETAIL_URL)
                .addHeaders("Authorization", "bearer " + access_token)
                .addBodyParameter(params)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            listener.contentDetailReceived(new ContentDetail(new JSONObject(response)));
                        } catch (JSONException e) {
                            listener.contentDetailDidFailWith(0, "Failed to convert json");
                        }
                        System.out.println("Content Detail response: ");
                        System.out.println(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.contentDetailDidFailWith(anError.getErrorCode(), anError.getMessage());
                    }
                });
    }

    @Override
    public void tokenDidFail(int errorCode, String message) {
        listener.contentDetailDidFailWith(errorCode, message);
    }


}
