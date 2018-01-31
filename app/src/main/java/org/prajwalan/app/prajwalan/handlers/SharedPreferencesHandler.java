package org.prajwalan.app.prajwalan.handlers;

import android.content.Context;
import android.content.SharedPreferences;

import org.prajwalan.app.prajwalan.DB.User;

/**
 * Created by Moiz on 21/01/2016.
 */
public class SharedPreferencesHandler {

    public static void storeUserDetails(Context c,User loggedInUser) {
        SharedPreferences sp = c.getSharedPreferences("Prajwalan", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean("isLoginDone", true);
        e.putString("fname", loggedInUser.fname);
        e.putString("lname", loggedInUser.lname);
        e.putString("email", loggedInUser.email);
        e.putString("mobile", loggedInUser.mobile);
        e.putString("college", loggedInUser.college);
        e.apply();
    }

    public static User getStoredUserDetails(Context c) {
        SharedPreferences sp = c.getSharedPreferences("Prajwalan", Context.MODE_PRIVATE);

        boolean isLoginDone = sp.getBoolean("isLoginDone", false);
        if(!isLoginDone) {
            return null;
        }

        User loggedInUser = new User();
        loggedInUser.fname = sp.getString("fname","");
        loggedInUser.lname = sp.getString("lname","");
        loggedInUser.email = sp.getString("email","");
        loggedInUser.mobile = sp.getString("mobile","");
        loggedInUser.college = sp.getString("college","");
        return loggedInUser;
    }

    public static void clearStoredUserDetails(Context c) {
        SharedPreferences sp = c.getSharedPreferences("Prajwalan", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.clear();
        e.apply();
    }

}
