package com.group12.fitnics.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.group12.fitnics.R;

import javax.security.auth.login.LoginException;

import static androidx.core.content.ContextCompat.startActivity;

public class Messages {
    public static void fatalError(final Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.fatalError));
        alertDialog.setMessage(message);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
             @Override
             public void onDismiss(DialogInterface dialogInterface) {
                 alertDialog.dismiss();
             }
         });

        alertDialog.show();
    }

    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }

    public static void alert(Activity owner, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.info_alert));
        alertDialog.setMessage(message);

        alertDialog.show();
    }

    public static AlertDialog welcome(Activity owner, String message, String title){
         boolean[] dismissed = {false};
        AlertDialog welcomeDialog = new AlertDialog.Builder(owner).create();

        welcomeDialog.setTitle(title);
        welcomeDialog.setMessage(message);
        welcomeDialog.setButton(Dialog.BUTTON_POSITIVE, "GOT IT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                welcomeDialog.dismiss();
            }
        });
        welcomeDialog.show();
        return welcomeDialog;
    }
}
