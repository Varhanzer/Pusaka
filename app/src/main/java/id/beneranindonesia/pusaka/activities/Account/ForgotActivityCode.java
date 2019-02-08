package id.beneranindonesia.pusaka.activities.Account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import id.beneranindonesia.pusaka.utils.LoadingDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.beneranindonesia.pusaka.R;

public class ForgotActivityCode extends AppCompatActivity implements View.OnClickListener {


    private LoadingDialog loadingDialog;
    private Button btnVerify;
    private Button btn_forgot_code_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_code);

        btnVerify = findViewById(R.id.btnVerify);
        btn_forgot_code_back = findViewById(R.id.btn_forgot_code_back);

        btn_forgot_code_back.setOnClickListener(this);
        btnVerify.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog(this);
        loadingDialog.showDialog();
        if (view == btnVerify) {


        } else if (view == btn_forgot_code_back) {
            loadingDialog.hideDialog();
            finish();
        }
    }
}
