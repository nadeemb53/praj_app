package org.prajwalan.app.prajwalan.FirebaseDispatcher;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.prajwalan.app.prajwalan.DB.DBUpdates;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Thread.sleep;


/**
 * Created by Slugger on 10/10/2017.
 */

public class BackgroundTask{

    public static String ACTION_IN_BACKGROUND = "background-activity";
    public static String TAG = "TAG!";
    public static int PRAJWALAN_DAY = 24 ;
    public static String PRAJWALAN_MONTH = "FEB" ;

    public static void executeTask(Context context, String action) throws InterruptedException {

        DateFormat d = new SimpleDateFormat("d");
        DateFormat m = new SimpleDateFormat("MMM");
        String day = d.format(Calendar.getInstance().getTime());
        String month = m.format(Calendar.getInstance().getTime());

        Log.d("data : ",day + " " + month);

        int days = 0;

        if(month.equals("Jan")){

             days = 31 - Integer.parseInt(day) + PRAJWALAN_DAY;
        }
        if(month.equals("Feb")){

            days = PRAJWALAN_DAY - Integer.parseInt(day);
        }
        NotificationUtils.remainderNotification(context , String.valueOf(days));

     /*   sleep(1000*60);
        DBUpdates[] updates;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(context);
            db.open();
            updates = db.getAllUpdatesData();
            db.close();

            if (updates == null) {
                throw new Exception();
            }
            else{
                for(int i = 0 ; i < updates.length+1 ; i++) {
                    if(updates[i].updates.equals("")){}
                    else {
                        NotificationUtils.updateNotification(context, String.valueOf(updates[i].updates));
                        sleep(1000 * 60);
                    }
                }
            }
        } catch (Exception e) {

            return;
        }
        */

    }

}

