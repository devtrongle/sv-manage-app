package com.sbp.manage.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.sbp.manage.databinding.WaitingDialogBinding;

public class Utility {
    private static Dialog sWaitingDialog;

    public static void showWaitingDialog(@NonNull Context context) {
        if (sWaitingDialog == null) {
            sWaitingDialog = new Dialog(context);
            sWaitingDialog.setContentView(WaitingDialogBinding.inflate(LayoutInflater.from(context)).getRoot());
            sWaitingDialog.setCancelable(false);
            sWaitingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        if (!sWaitingDialog.isShowing()) {
            sWaitingDialog.show();
        }
    }

    public static void dismissWaitingDialog() {
        if (sWaitingDialog != null && sWaitingDialog.isShowing()) {
            sWaitingDialog.dismiss();
        }
    }
}
