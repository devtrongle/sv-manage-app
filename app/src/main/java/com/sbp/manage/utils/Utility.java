package com.sbp.manage.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.sbp.manage.databinding.WaitingDialogBinding;
import com.sbp.manage.network.dto.LoginDto;

import java.text.DecimalFormat;

public class Utility {
    private static Dialog sWaitingDialog;

    public static void showWaitingDialog(@NonNull Context context) {
        if (sWaitingDialog == null) {
            sWaitingDialog = new Dialog(context);
            sWaitingDialog.setContentView(
                    WaitingDialogBinding.inflate(LayoutInflater.from(context)).getRoot());
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

    public static void saveLogin(Context context, LoginDto loginDto) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login", new Gson().toJson(loginDto));
        editor.apply();
    }

    public static void removeLogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("login");
        editor.apply();
    }

    public static LoginDto getLogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data",
                Context.MODE_PRIVATE);
        String strLogin = sharedPreferences.getString("login", "");
        if (strLogin.isEmpty()) {
            return null;
        } else {
            return new Gson().fromJson(strLogin, LoginDto.class);
        }
    }

    public static String currencyFormat(double money) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(money) + " VND";
    }
}
