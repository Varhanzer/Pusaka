package id.beneranindonesia.pusaka.activities.Account;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;
import id.beneranindonesia.pusaka.api.SignInAPI;
import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.utils.Session;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, SignInAPI.Listener {

    private Button btn_get_started;
    private Button btn_login_back;
    private EditText txt_email;
    private EditText txt_password;
    private TextView txt_register;
    private TextView txt_forgot;

    private SignInAPI signInAPI;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_email       = findViewById(R.id.txtEmail);
        txt_password    = findViewById(R.id.txtPassword);
        txt_register    = findViewById(R.id.txtToRegisterActivity);
        txt_forgot      = findViewById(R.id.tv_forget_password);
        btn_get_started = findViewById(R.id.btn_get_started);
        btn_login_back  = findViewById(R.id.btn_login_back);


        btn_get_started.setOnClickListener(this);
        btn_login_back.setOnClickListener(this);
        txt_register.setOnClickListener(this);
        txt_forgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == txt_forgot){
            Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
            startActivity(intent);
        }
        if (view == txt_register){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        }
        if (view == btn_get_started) {
            if (loadingDialog == null)
                loadingDialog = new LoadingDialog(this);
            loadingDialog.showDialog();

            if (signInAPI == null) {
                signInAPI = new SignInAPI();
            }
            signInAPI.listener = this;
            signInAPI.signIn(getApplicationContext(), txt_email.getText().toString(), txt_password.getText().toString());
        } else if (view == btn_login_back) {
            finish();
        }
    }

    @Override
    public void signInSuccess() {

        loadingDialog.hideDialog();
        Session.getInstance().initalizeSession(this);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void signInFailed(int statusCode, String message) {
        loadingDialog.hideDialog();
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
