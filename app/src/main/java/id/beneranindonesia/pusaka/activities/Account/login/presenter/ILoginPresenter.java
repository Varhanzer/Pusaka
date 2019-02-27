package id.beneranindonesia.pusaka.activities.Account.login.presenter;

public interface ILoginPresenter {

    void clear();
    void beginLogin(String username, String password);
    void showLoadingDialog();
    void hideLoadingDialog();

}
