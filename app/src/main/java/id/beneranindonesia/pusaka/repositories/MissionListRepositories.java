package id.beneranindonesia.pusaka.repositories;

import android.arch.lifecycle.MutableLiveData;
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
import java.util.List;

import id.beneranindonesia.pusaka.api.GetDataService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.api.Token;
import id.beneranindonesia.pusaka.api.Token_1;
import id.beneranindonesia.pusaka.api.URLs;
import id.beneranindonesia.pusaka.models.ContentList;
import id.beneranindonesia.pusaka.models.Contents;
import id.beneranindonesia.pusaka.models.Mission;
import id.beneranindonesia.pusaka.utils.Session;
import id.beneranindonesia.pusaka.utils.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissionListRepositories implements Token.TokenListener {

    private static MissionListRepositories instance;

    private final String TAG = "MissionListRepositories";

    private ArrayList<Mission> dataSet = new ArrayList<>();

    private HashMap<String, String> params;

    public static MissionListRepositories getInstance() {
        if(instance == null) {
            instance = new MissionListRepositories();
        }
        return  instance;
    }

    public void getMissions() {
    }

    @Override
    public void tokenReceived(String access_token, String token_Type, String sid, String expires_in) {

    }

    @Override
    public void tokenDidFail(int errorCode, String message) {

    }
}














































