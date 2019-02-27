package id.beneranindonesia.pusaka.activities.Account.login.view;

import id.beneranindonesia.pusaka.models.User;

public interface ILoginView {

    public void onClearText();
    public void onLoginSuccess(User user);
    public void onLoginFailed(String message);
    public void onShowLoadingDialog();
    public void onHideLoadingDialog();

}
