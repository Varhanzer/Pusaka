package id.beneranindonesia.pusaka.activities.Account.login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Account.ForgotActivity;
import id.beneranindonesia.pusaka.activities.Account.register.RegisterActivity;
import id.beneranindonesia.pusaka.activities.Account.login.presenter.ILoginPresenter;
import id.beneranindonesia.pusaka.activities.Account.login.presenter.LoginPresenterCompl;
import id.beneranindonesia.pusaka.activities.MainActivity;
import id.beneranindonesia.pusaka.models.User;
import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.utils.Session;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.btn_get_started)
    Button btn_get_started;

    @BindView(R.id.btn_login_back)
    Button btn_login_back;

    @BindView(R.id.txtEmail)
    EditText txt_email;

    @BindView(R.id.txtPassword)
    EditText txt_password;

    @BindView(R.id.txtToRegisterActivity)
    TextView txt_register;

    @BindView(R.id.tv_forget_password)
    TextView txt_forgot;

    private LoadingDialog loadingDialog;
    private ILoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterCompl(this);

        txt_forgot.setOnClickListener(view ->
            startActivity(new Intent(LoginActivity.this, ForgotActivity.class))
        );

        txt_register.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class))
        );

        btn_login_back.setOnClickListener(view ->
            finish()
        );

        btn_get_started.setOnClickListener(view -> {
            String email = txt_email.getText().toString();
            String password = txt_password.getText().toString();
            loginPresenter.showLoadingDialog();
            loginPresenter.beginLogin(email, password);
        });

    }

    @Override
    public void onClearText() {

    }

    @Override
    public void onLoginSuccess(User user) {
        Session.getInstance().saveUser(this, user.getUserID(), user.getNickName(), user.getImageFile());
        Session.getInstance().initalizeSession(this);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onLoginFailed(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowLoadingDialog() {
        if (loadingDialog == null) loadingDialog = new LoadingDialog(this);
        loadingDialog.showDialog();
    }

    @Override
    public void onHideLoadingDialog() {
        loadingDialog.hideDialog();
    }

}
















