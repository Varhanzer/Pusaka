package id.beneranindonesia.pusaka.api;

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

public class ContentListAPI implements Token.TokenListener {

    public interface Listener {
        void contentListReceived(ArrayList<ContentList> list);
        void contentListDidFailWith(int statusCode, String message);
    }

    private Token token;
    private HashMap<String, String> params;

    public Listener listener;

    public void get() {
        try {
            JSONObject json = new JSONObject();
            json.put("lang", "id");
            json.put("userID", Session.getInstance().getUserID());

            HashMap<String, String > params = new HashMap<>();
            params.put("pikachu", json.toString());

            this.params  = params;
            token = new Token();
            token.listener = this;
            token.get();
        } catch (JSONException e) { listener.contentListDidFailWith(0, "Failed to create JSONObject"); }
    }

    @Override
    public void tokenReceived(String access_token, String token_Type, String sid, String expires_in) {
        AndroidNetworking.post(URLs.CONTENT_LIST_URL)
                .addHeaders("Authorization", "bearer " + access_token)
                .addBodyParameter(params)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Content list response: ");
                        System.out.println(response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray listContent = obj.getJSONArray("listContent");

                            ArrayList<ContentList> contentLists = new ArrayList<>();

                            for(int i = 0; i < listContent.length(); i++) {
                                contentLists.add(new ContentList(listContent.getJSONObject(i)));
                            }

                            listener.contentListReceived(contentLists);

                        } catch (JSONException e) {
                            listener.contentListDidFailWith(0, "General Error");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        listener.contentListDidFailWith(anError.getErrorCode(), anError.getMessage());
                    }
                });
    }

    @Override
    public void tokenDidFail(int errorCode, String message) {
        System.out.println("Error code: " + errorCode);
        System.out.println("Message: " + message);
        listener.contentListDidFailWith(errorCode, message);
    }
}




















