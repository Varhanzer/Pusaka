package id.beneranindonesia.pusaka.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import id.beneranindonesia.pusaka.api.GetDataService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.api.Token_1;
import id.beneranindonesia.pusaka.models.Contents;
import id.beneranindonesia.pusaka.models.Mission;
import id.beneranindonesia.pusaka.repositories.MissionListRepositories;
import id.beneranindonesia.pusaka.utils.Session;
import id.beneranindonesia.pusaka.utils.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissionListViewModel extends ViewModel {

    private MutableLiveData<List<Mission>> mMissions;

    private MissionListRepositories mRepo;

    private String TAG = "MissionListViewModel";

    public MissionListViewModel() {

    }

    public void init() {
        if (mRepo != null)
            return;
        mRepo = MissionListRepositories.getInstance();
    }

    public LiveData<List<Mission>> getMissions(Context context) {
        if(mMissions == null) {
            mMissions = new MutableLiveData<>();
            loadMissions(context);
        }
        return mMissions;
    }

    private void loadMissions(final Context context) {
        try {
            GetDataService service = RetrofitClientInstance.getInstance().create(GetDataService.class);

            JSONObject json = new JSONObject();
            json.put("lang", "id");
            json.put("userID", Session.getInstance().getUserID());

            HashMap<String, String> header = new HashMap<>();
            header.put("Authorization", "bearer " + TokenManager.ACCESS_TOKEN);

            HashMap<String, String> body = new HashMap<>();
            body.put("pikachu", json.toString());

            Call<Contents> call = service.getMissions(header, body);

            call.enqueue(new Callback<Contents>() {
                @Override
                public void onResponse(Call<Contents> call, Response<Contents> response) {

                    if(response.code() == 200 && response.body() != null) {

                        Log.d("MissionListViewModel", "list content received");

                        mMissions.setValue(response.body().getListContent());

                        for(Mission mission: response.body().getListContent()) {
                            Log.d("Mission Name", mission.getMissionName());
                        }

                    } else if (response.code() == 401) {

                        TokenManager tokenManager = new TokenManager();
                        tokenManager.listener = new TokenManager.Listener() {

                            @Override
                            public void getTokenSuccess(Token_1 token) {
                                loadMissions(context);
                            }

                            @Override
                            public void getTokenFailed(int errorCode, String message) {
                                Log.e("Token Error", errorCode + message);
                            }

                        };

                        tokenManager.getToken(context);

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Contents> call, Throwable t) {
                    Log.d("MISSIONLISTIVEW TOKEN", t.getMessage());
                }
            });
        } catch (JSONException e) {

            Log.d("TAG", e.getMessage());

        }
    }

}










































