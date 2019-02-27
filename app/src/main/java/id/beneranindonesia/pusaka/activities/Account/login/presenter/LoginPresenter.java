package id.beneranindonesia.pusaka.activities.Account.login.presenter;

import id.beneranindonesia.pusaka.activities.Account.login.view.ILoginView;
import id.beneranindonesia.pusaka.api.ApiService;

public class LoginPresenter implements ILoginPresenter {

    ILoginView iLoginView;
    ApiService apiService;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void clear() {

    }

    @Override
    public void beginLogin(String email, String password) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

}


































