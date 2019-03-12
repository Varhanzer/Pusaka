package id.beneranindonesia.pusaka.activities.Account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import id.beneranindonesia.pusaka.api.ApiService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_SendCode;
    private Button btn_forgot_back;
    private LoadingDialog loadingDialog;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        btn_SendCode     = findViewById(R.id.btnSendCode);
        btn_forgot_back  = findViewById(R.id.btn_forgot_back);
        email = findViewById(R.id.txt_forgot_email);

        btn_forgot_back.setOnClickListener(this);
        btn_SendCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog(this);
        loadingDialog.showDialog();
        if (view == btn_SendCode) {
            Intent intent = new Intent(this, ForgotActivityCode.class);
            startActivity(intent);
            loadingDialog.hideDialog();

        } else if (view == btn_forgot_back) {
            finish();
        }
    }

    private void ForgotPassword(){
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", email.getText().toString());

                HashMap <String, String> params = new HashMap<>();
                params.put("pikachu",jsonObject.toString());

                ApiService apiService = RetrofitClientInstance.getInstance().create(ApiService.class);
                apiService.forgot(params).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.code()==200){

                        }

                        else if(response.code()==901){

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
            catch (JSONException e){

            }
    }
}
