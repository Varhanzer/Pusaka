package id.beneranindonesia.pusaka.api;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import java.util.HashMap;

public class SignUpAPI implements Token.TokenListener {

    public interface SignUpAPiListener {
        void signUpSuccess();
        void signUpFailed(int errorCode, String message);
    }

    private HashMap<String, String> params;
    private Token token;
    public SignUpAPiListener listener;

    public void register(HashMap<String, String> params) {
        this.params = params;
        token = new Token();
        token.listener = this;
        token.get();

    }

    @Override
    public void tokenReceived(String access_token, String token_Type, String sid, String expires_in) {
       AndroidNetworking.post(URLs.SIGN_UP_URL)
               .addHeaders("Authorization", "bearer " + access_token)
               .addBodyParameter(params)
               .setPriority(Priority.IMMEDIATE)
               .build()
               .getAsString(new StringRequestListener() {
                   @Override
                   public void onResponse(String response) {
                       listener.signUpSuccess();
                   }
                   @Override
                   public void onError(ANError anError) {
                       listener.signUpFailed(anError.getErrorCode(), anError.getMessage());
                   }
               });
    }

    @Override
    public void tokenDidFail(int errorCode, String message) {
        listener.signUpFailed(errorCode, message);
    }

}






















































