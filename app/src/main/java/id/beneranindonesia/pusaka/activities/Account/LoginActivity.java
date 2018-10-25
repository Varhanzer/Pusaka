package id.beneranindonesia.pusaka.activities.Account;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;
import id.beneranindonesia.pusaka.api.SignInAPI;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, SignInAPI.Listener {

    private Button btn_get_started;
    private Button btn_login_back;
    private EditText txt_email;
    private EditText txt_password;

    private SignInAPI signInAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_email       = findViewById(R.id.txtEmail);
        txt_password    = findViewById(R.id.txtPassword);
        btn_get_started = findViewById(R.id.btn_get_started);
        btn_login_back  = findViewById(R.id.btn_login_back);

        btn_get_started.setOnClickListener(this);
        btn_login_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_get_started) {
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void signInFailed(int statusCode, String message) {

    }
}
