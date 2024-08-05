package hoanglv.fpoly.thithu.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import hoanglv.fpoly.thithu.R;

public class LoadingDialog {
    Activity activity;
    AlertDialog alertDialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_progress_dialog, null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    void dismiss() {
        alertDialog.dismiss();
    }
}
