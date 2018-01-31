package org.prajwalan.app.prajwalan;

/**
 * Created by Nadeem on 04/01/2018.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import org.prajwalan.app.prajwalan.handlers.JSONHandler;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;
import org.prajwalan.app.prajwalan.utility.CommonUtilities;
import org.prajwalan.app.prajwalan.utility.NetworkConnection;
import org.prajwalan.app.prajwalan.utility.SendToServerUtility;

public class Flash extends AppCompatActivity {

    boolean splashComplete1,splashComplete2,splashComplete3;
    boolean dataLoadComplete;
    boolean onNextPage= false;

    RelativeLayout rl;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        /*Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent("org.prajwalan.app.prajwalan.MainActivity");
                    startActivity(i);
                }
            }
        };
        timer.start();*/

        rl = (RelativeLayout)findViewById(R.id.parentLayout);
             try {
            SQLiteDbHandler db = new SQLiteDbHandler(this);
            db.open();
            date = db.getDateData();
            db.close();
        }
        catch (Exception e){
            date = null;
        }


        splashComplete1 = false;
        splashComplete2 = false;
        splashComplete3 = false;
        dataLoadComplete = false;

        if(!NetworkConnection.isConnectingToInternet(getApplicationContext())) {
            Snackbar s = Snackbar.make(rl, "Please Connect to Internet to get Latest Details.", Snackbar.LENGTH_INDEFINITE);
            CommonUtilities.displayErrorMessage(s);
        }

        Thread timer1 = new Thread() {

            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                } finally {
                    splashComplete1 = true;
                    showNextActivity();
                }
            }
        };
        timer1.start();

        Thread timer2 = new Thread() {

            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                } finally {
                    splashComplete2 = true;
                    showNextActivity();
                }
            }
        };
        timer2.start();

        Thread timer3 = new Thread() {

            public void run() {
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                   // e.printStackTrace();
                } finally {
                    splashComplete3 = true;
                    showNextActivity();
                }
            }
        };
        timer3.start();

        String requestURL = "http://www.prajwalan.org/app/getAllDataAsJson.php";
        try {
            SendToServerUtility requestData = new SendToServerUtility(requestURL);
            if(date != null) {
                requestData.addFormField("lastdate", date);
            }
            RequestDataFromServer sendRequest = new RequestDataFromServer();
            sendRequest.execute(requestData);

        } catch (Exception ex) {
            dataLoadComplete = true;
            showNextActivity();
        }

    }

    void showNextActivity() {

        if(onNextPage)
            return;

        if(splashComplete3 && !dataLoadComplete && date == null) {
            //CommonUtilities.displayAlertMessage(getBaseContext(), "Failed", "Failed to fetch data from server.");
            Snackbar s = Snackbar.make(rl,"Failed to fetch data from server...",Snackbar.LENGTH_LONG).setAction("Continue", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("org.prajwalan.app.prajwalan.MainActivity");
                    startActivity(i);
                }
            }).setCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    super.onDismissed(snackbar, event);
                    Intent i = new Intent("org.prajwalan.app.prajwalan.MainActivity");
                    startActivity(i);
                }
            });

            CommonUtilities.displayErrorMessage(s);
            onNextPage = true;
        }
        else if(splashComplete3) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            onNextPage = true;
        }
        else if(splashComplete2 && date != null) {

            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            onNextPage = true;
        }
        else if(splashComplete2 && date == null) {
            Snackbar s = Snackbar.make(rl,"Seems You are on a slow connection..",Snackbar.LENGTH_INDEFINITE);
            CommonUtilities.displayMessage(s);
        }
        else if(splashComplete1 && !dataLoadComplete) {
            Snackbar s = Snackbar.make(rl,"Fetching data..",Snackbar.LENGTH_INDEFINITE);
            CommonUtilities.displayMessage(s);
        }
        else if(splashComplete1 && dataLoadComplete) {
            Intent i = new Intent("org.prajwalan.app.prajwalan.MainActivity");
            startActivity(i);
            onNextPage = true;
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }


    public class RequestDataFromServer extends AsyncTask<SendToServerUtility, Void, String> {

        @Override
        protected String doInBackground(SendToServerUtility... arg0) {

            String result = "{}";
            try {
                SendToServerUtility requestData = arg0[0];
                result = requestData.send();
            }
            catch(Exception e) {
                result = e.toString();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONHandler json = new JSONHandler(getBaseContext());
                json.parseNStore(result);
            }
            catch(Exception e) {
                //Toast.makeText(getBaseContext(),e.toString(), Toast.LENGTH_LONG).show();
            }
            finally {
                dataLoadComplete = true;
                showNextActivity();
            }
        }

    }
}
