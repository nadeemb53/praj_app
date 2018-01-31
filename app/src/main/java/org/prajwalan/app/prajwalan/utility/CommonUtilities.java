package org.prajwalan.app.prajwalan.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Moiz on 10/01/2016.
 */
public class CommonUtilities {

    public static void displayMessage(Snackbar s) {

        s.setActionTextColor(Color.WHITE);

        View sbView = s.getView();
        sbView.setPadding(0, 0, 0, 0);
        sbView.setBackgroundColor(Color.rgb(0, 158, 223));

        TextView tv = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(16);

        TextView sbActionView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_action);
        sbActionView.setBackgroundColor(Color.rgb(33, 54, 73));
        sbActionView.setTextSize(17);

        s.show();
    }

    public static void displayErrorMessage(Snackbar s) {

        s.setActionTextColor(Color.WHITE);

        View sbView = s.getView();
        sbView.setPadding(0, 0, 0, 0);
        sbView.setBackgroundColor(Color.argb(180, 150, 50, 50));

        TextView tv = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(16);

        TextView sbActionView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_action);
        sbActionView.setBackgroundColor(Color.argb(180, 33, 54, 73));
        sbActionView.setTextSize(17);

        s.show();
    }

    public static void displayAlertMessage(Context c,String title,String msg) {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(c);
        alertBuilder.setMessage(msg);
        alertBuilder.setCancelable(false);
        alertBuilder.setPositiveButton("OK", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = alertBuilder.create();
        alert.setTitle(title);
        alert.show();
    }

}
