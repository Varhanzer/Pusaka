package id.beneranindonesia.pusaka.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import id.beneranindonesia.pusaka.R;

public class LoadingDialog {

    Activity activity;
    Dialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.show();
    }

    public void hideDialog() {
        dialog.dismiss();
    }

}

























































