package id.beneranindonesia.pusaka.activities.Account.register;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Account.login.view.LoginActivity;
import id.beneranindonesia.pusaka.activities.MainActivity;
import id.beneranindonesia.pusaka.api.ApiService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.api.SignUpAPI;
import id.beneranindonesia.pusaka.models.User;
import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgGender;

    @BindView(R.id.btn_register)
    Button registerBtn;

    @BindView(R.id.txtToLoginActivity)
    TextView txtSignIn;

    @BindView(R.id.txt_full_name)
    EditText etFullName;

    @BindView(R.id.txt_school)
    EditText etSchool;

    private EditText et_account_name, et_email, et_password, et_confirm_password;

    private GradientDrawable registerBtnShape;
    private SignUpAPI signUpAPI;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName         = findViewById(R.id.txt_full_name);
        etSchool           = findViewById(R.id.txt_school);
        et_account_name     = findViewById(R.id.txt_account_name);
        et_email            = findViewById(R.id.txt_email);
        et_password         = findViewById(R.id.txt_password);
        et_confirm_password = findViewById(R.id.txt_confirm_password);

        AndroidNetworking.initialize(getApplicationContext());

        registerBtn = findViewById(R.id.btn_register);
        registerBtn.setOnClickListener(this);

        txtSignIn = findViewById(R.id.txtToLoginActivity);
        txtSignIn.setOnClickListener(this);

        LayerDrawable layerDrawable = (LayerDrawable) registerBtn.getBackground();
        registerBtnShape = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.drawable_button);
        //Set Default Color
        registerBtnShape.setColor(getResources().getColor(R.color.java_blue));

        Button backBtn = (Button) findViewById(R.id.btn_register_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rgGender = findViewById(R.id.rg_gender);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                ImageView imgGender = findViewById(R.id.img_gender);

                if (checkedId == R.id.radio_male) {
                    imgGender.setImageDrawable(getResources().getDrawable(R.drawable.boy, getApplicationContext().getTheme()));
                    registerBtnShape.setColor(getResources().getColor(R.color.java_blue));
                } else if (checkedId == R.id.radio_female) {
                    imgGender.setImageDrawable(getResources().getDrawable(R.drawable.girl, getApplicationContext().getTheme()));
                    registerBtnShape.setColor(getResources().getColor(R.color.pink));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view ==txtSignIn){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        if (view == registerBtn) {
            String fullname        = et_fullname.getText().toString();
            String schoolName      = et_school.getText().toString();
            final String accountName     = et_account_name.getText().toString();
            String email           = et_email.getText().toString();
            String username        = et_account_name.getText().toString();
            String password        = et_password.getText().toString();
            String confirmPassword = et_confirm_password.getText().toString();
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Password kamu tidak sama, coba dicek lagi", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject json = new JSONObject();
                json.put("email", email);
                json.put("password", password);
                json.put("lang", "id");
                json.put("userName", username);
                json.put("fullName", fullname);
                json.put("cardType", 1);
                json.put("cardImage", "woahhhah.jpg");
                json.put("cardNumber", "3882348484842");

                HashMap<String, String> params = new HashMap<>();
                params.put("pikachu", json.toString());

                if (loadingDialog == null)
                    loadingDialog = new LoadingDialog(this);
                loadingDialog.showDialog();

                ApiService service = RetrofitClientInstance.getInstance().create(ApiService.class);
                Call<User> call = service.register(params);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        loadingDialog.hideDialog();
                        if(response.code() == 200 && response.body() != null) {
                            Session.getInstance().saveUser(RegisterActivity.this, response.body().getUserID(), accountName, "");
                            Session.getInstance().initalizeSession(RegisterActivity.this);
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this, response.body().getUserID(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        loadingDialog.hideDialog();
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}















































