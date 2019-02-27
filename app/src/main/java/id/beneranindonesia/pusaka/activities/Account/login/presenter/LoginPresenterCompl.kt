package id.beneranindonesia.pusaka.activities.Account.login.presenter

import android.util.Log
import id.beneranindonesia.pusaka.activities.Account.login.view.ILoginView
import id.beneranindonesia.pusaka.api.ApiService
import id.beneranindonesia.pusaka.api.RetrofitClientInstance
import id.beneranindonesia.pusaka.models.ContentDetail
import id.beneranindonesia.pusaka.models.User
import id.beneranindonesia.pusaka.utils.Session
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class LoginPresenterCompl(val iLoginView: ILoginView): ILoginPresenter {

    private val apiService = RetrofitClientInstance.getInstance().create(ApiService::class.java)


    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun beginLogin(email: String?, password: String?) {
        val json = JSONObject()
            json.put("email", email)
            json.put("password", password)

        val params = HashMap<String, String>()
            params["pikachu"] = json.toString()

        apiService.login(params).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                iLoginView.onHideLoadingDialog()
                if(response.code() == 200 && response.body() != null ) {
                    iLoginView.onLoginSuccess(response.body())
                } else {
                    iLoginView.onLoginFailed(response.message())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                iLoginView.onHideLoadingDialog()
                iLoginView.onLoginFailed(t.message)
            }
        })

    }

    override fun showLoadingDialog() {
        iLoginView.onShowLoadingDialog()
    }

    override fun hideLoadingDialog() {
        iLoginView.onHideLoadingDialog()
    }
}


































