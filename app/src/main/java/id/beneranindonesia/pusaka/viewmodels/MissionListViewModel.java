package id.beneranindonesia.pusaka.viewmodels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import id.beneranindonesia.pusaka.api.ApiService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.models.Contents;
import id.beneranindonesia.pusaka.models.Mission;
import id.beneranindonesia.pusaka.repositories.MissionListRepositories;
import id.beneranindonesia.pusaka.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissionListViewModel extends ViewModel {

    private MutableLiveData<List<Mission>> mMissions;

    private MissionListRepositories mRepo;

    private String TAG = "MissionListViewModel";

    public MissionListViewModel() {

    }

    public LiveData<List<Mission>> getMissions() {
        if(mMissions == null) {
            mMissions = new MutableLiveData<>();
        }
        loadMissions();
        return mMissions;
    }

    private void loadMissions() {
        try {

            ApiService service = RetrofitClientInstance.getInstance().create(ApiService.class);

            JSONObject json = new JSONObject();
            json.put("lang", "id");
            json.put("userID", Session.getInstance().getUserID());

            HashMap<String, String> params = new HashMap<>();
            params.put("pikachu", json.toString());

            Call<Contents> call = service.getMissions(params);

            call.enqueue(new Callback<Contents>() {
                @Override
                public void onResponse(Call<Contents> call, Response<Contents> response) {
                    if(response.code() == 200 && response.body() != null) {
                        mMissions.setValue(response.body().getListContent());
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










































