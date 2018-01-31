package org.prajwalan.app.prajwalan.FirebaseDispatcher;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Slugger on 10/10/2017.
 */

public class DatabaseUpdateIntentService extends IntentService {

    public DatabaseUpdateIntentService() {
        super("DatabaseUpdateIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        try {
            BackgroundTask.executeTask(this,action);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //

    }
}
