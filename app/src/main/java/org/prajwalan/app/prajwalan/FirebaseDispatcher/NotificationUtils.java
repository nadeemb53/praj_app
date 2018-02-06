package org.prajwalan.app.prajwalan.FirebaseDispatcher;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import org.prajwalan.app.prajwalan.MainActivity;
import org.prajwalan.app.prajwalan.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Slugger on 10/10/2017.
 */

public class NotificationUtils {
    private static final int NOTIFICATION_INTENT_ID =7032;


    public static void remainderNotification(Context context , String days){


//        Toast.makeText(context,"hellow",Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(largeIcon(context))
                .setContentTitle("PRAJWALAN")
                .setContentText("Hurry up! Only "+days+" days remaining.")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Prajwalan"))
               // .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(false);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_INTENT_ID,notificationBuilder.build());
    }
    public static void updateNotification(Context context , String days){

//        Toast.makeText(context,"hellow",Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(largeIcon(context))
                .setContentTitle("PRAJWALAN UPDATE")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(days))
                // .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(false);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_INTENT_ID,notificationBuilder.build());
    }

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                NOTIFICATION_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context){
        Resources resources = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(resources, R.drawable.logo);
        return largeIcon;
    }
    public String getTitle(){
        return null;
    }
}
