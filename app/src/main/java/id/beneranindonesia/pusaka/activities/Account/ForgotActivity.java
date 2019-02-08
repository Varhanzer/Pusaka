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

import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.R;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_SendCode;
    private Button btn_forgot_back;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        btn_SendCode     = findViewById(R.id.btnSendCode);
        btn_forgot_back  = findViewById(R.id.btn_forgot_back);

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
}
