package org.prajwalan.app.prajwalan;

import android.content.Context;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;


import java.util.concurrent.TimeUnit;

/**
 * Created by Slugger on 10/10/2017.
 */

public class BackgroundTasksUtilities {

    private static final int INTERVAL_MINUTES = 1;
    private static final int INTERVAL_SECONDS = (int) (TimeUnit.MINUTES.toSeconds(INTERVAL_MINUTES));
    private static final int SYNC_FLEXTIME_SECONDS = INTERVAL_SECONDS;

    private static final String JOB_TAG = "background-task-tag";

    private static boolean initiate ;

    synchronized public static void scheduleForegroundServices( final Context context){

        Log.d("call :" ,"success");
        if(initiate) return;

        GooglePlayDriver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constraintJob = dispatcher.newJobBuilder()
                .setService(KCTFirebaseJobService.class)
                .setTag(JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setConstraints(Constraint.ON_UNMETERED_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0,60*60))
                .setReplaceCurrent(true)
                .build();

        dispatcher.mustSchedule(constraintJob);

        initiate = true;
    }

}
