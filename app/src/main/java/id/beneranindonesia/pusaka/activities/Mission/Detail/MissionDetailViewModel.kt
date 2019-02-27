package id.beneranindonesia.pusaka.activities.Mission.Detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import id.beneranindonesia.pusaka.api.ApiService
import id.beneranindonesia.pusaka.api.RetrofitClientInstance
import id.beneranindonesia.pusaka.models.ContentDetail
import id.beneranindonesia.pusaka.utils.Session
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MissionDetailViewModel: ViewModel() {

    private val apiService = RetrofitClientInstance.getInstance().create(ApiService::class.java)

    private val missionDetail = MutableLiveData<ContentDetail>()


    fun getMissionDetail(missionID: String): LiveData<ContentDetail> {
        loadMissionDetail(missionID)
        return missionDetail
    }

    private fun loadMissionDetail(missionID: String) {
        val json = JSONObject()
            json.put("lang", "id")
            json.put("missionID", missionID)
            json.put("userID", Session.getInstance().userID)

        val params = HashMap<String, String>()
            params["pikachu"] = json.toString()

        val call = apiService.getMissionDetail(params)
        call.enqueue(object : Callback<ContentDetail> {
            override fun onResponse(call: Call<ContentDetail>, response: Response<ContentDetail>) {
                Log.d("MissionDetailViewModel", "Response received")
                Log.d("MissionDetailViewModel", "Code " + response.code())
                if (response.code() == 200 && response.body() != null) {
                    missionDetail.value = response.body()
                }
            }
            override fun onFailure(call: Call<ContentDetail>, t: Throwable) {

            }
        })
    }

}




































