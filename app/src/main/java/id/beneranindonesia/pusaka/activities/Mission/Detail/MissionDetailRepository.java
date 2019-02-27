package id.beneranindonesia.pusaka.activities.Mission.Detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import id.beneranindonesia.pusaka.api.ApiService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.models.ContentDetail;
import id.beneranindonesia.pusaka.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissionDetailRepository {

    private ApiService apiService;

    public MissionDetailRepository() {
        apiService = RetrofitClientInstance.getInstance().create(ApiService.class);
    }

    public LiveData<ContentDetail> getMissionDetail(String missionID) {
        final MutableLiveData<ContentDetail> data = new MutableLiveData<>();

        try {
            JSONObject json = new JSONObject();
            json.put("lang", "id");
            json.put("userID", Session.getInstance().getUserID());
            json.put("missionID", missionID);

            HashMap<String, String > params = new HashMap<>();
            params.put("pikachu", json.toString());

            apiService.getMissionDetail(params).enqueue(new Callback<ContentDetail>() {
                @Override
                public void onResponse(Call<ContentDetail> call, Response<ContentDetail> response) {
                    if (response.isSuccessful()) {
                        data.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<ContentDetail> call, Throwable t) {
                    data.setValue(null);
                }
            });
        } catch (JSONException e) {

        }
        return data;
    }

}
































