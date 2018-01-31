package org.prajwalan.app.prajwalan;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.prajwalan.app.prajwalan.DB.User;
import org.prajwalan.app.prajwalan.handlers.JSONHandler;
import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;
import org.prajwalan.app.prajwalan.handlers.SharedPreferencesHandler;
import org.prajwalan.app.prajwalan.utility.CommonUtilities;
import org.prajwalan.app.prajwalan.utility.NetworkConnection;
import org.prajwalan.app.prajwalan.utility.RoundedBitmapDrawableUtility;
import org.prajwalan.app.prajwalan.utility.SendToServerUtility;

public class tweek extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Button.OnClickListener {
    private ImageView iv1;
    private Bitmap bit1;
    private Resources mResources;

    private View headerView;

    private ProgressDialog pd;
    Snackbar s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweek);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/sf.ttf");
        TextView text=(TextView)findViewById(R.id.text);
        text.setTypeface(tf);
        ImageView logo=(ImageView)findViewById(R.id.img_eve);
        logo.setImageResource(R.drawable.inlogo);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.logo_anim);
        set.setTarget(logo);
        set.start();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mResources = getResources();

        /*iv1=(ImageView)findViewById(R.id.tweek1);
        bit1= BitmapFactory.decodeResource(mResources, R.drawable.moviemaking);
        RoundedBitmapDrawable drawable2 = RoundedBitmapDrawableUtility.createRoundedBitmapDrawableWithBorder(bit1, mResources);
        iv1.setImageDrawable(drawable2);



        iv1=(ImageView)findViewById(R.id.tweek3);
        bit1= BitmapFactory.decodeResource(mResources, R.drawable.autocad);
        drawable2 = RoundedBitmapDrawableUtility.createRoundedBitmapDrawableWithBorder(bit1, mResources);
        iv1.setImageDrawable(drawable2);

        iv1=(ImageView)findViewById(R.id.tweek4);
        bit1= BitmapFactory.decodeResource(mResources, R.drawable.calligraphy);
        drawable2 = RoundedBitmapDrawableUtility.createRoundedBitmapDrawableWithBorder(bit1, mResources);
        iv1.setImageDrawable(drawable2);*/



        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();

       /* pd = new ProgressDialog(this);

        Button b1 = (Button) findViewById(R.id.movieRegister);

        Button b3 = (Button) findViewById(R.id.autocad);
        Button b4 = (Button) findViewById(R.id.caligraphyRegister);

        b1.setOnClickListener(this);

        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/sf.ttf");
        b1.setTypeface(face);

        b3.setTypeface(face);
        b4.setTypeface(face);

        TextView tv = (TextView) findViewById(R.id.heading1);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading4);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading3);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.date1);
        tv.setTypeface(face);

        tv = (TextView) findViewById(R.id.date3);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.date4);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading4);
        tv.setTypeface(face);*/
    }

    @Override
    public void onClick(View v) {/*

        if(s!=null && s.isShownOrQueued()) {
            s.dismiss();
        }
        if(!NetworkConnection.isConnectingToInternet(this)) {
            s = Snackbar.make(v, "Cannot Connect To Internet...", Snackbar.LENGTH_INDEFINITE).setAction("Try Again",this);
            CommonUtilities.displayErrorMessage(s);
            return;
        }

        User loggedInUser = SharedPreferencesHandler.getStoredUserDetails(getApplicationContext());
        if(loggedInUser == null) {
            Snackbar s = Snackbar.make(v, "Please Login to Register for the TechWeek", Snackbar.LENGTH_INDEFINITE).setAction("Go To Login", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(tweek.this,Register_Activity.class);
                    startActivity(i);
                }
            });
            CommonUtilities.displayErrorMessage(s);
            return;
        }

        pd.setMessage("Registering For Workshop...");
        pd.setTitle("Please Wait");
        pd.setCancelable(false);
        pd.show();

        String techweekWorkshopToRegister = "";
        switch (v.getId()) {
            case R.id.movieRegister :       techweekWorkshopToRegister = "Movie Making";
                                            break;

            case R.id.autocad :       techweekWorkshopToRegister = "Autocad";
                                            break;
            case R.id.caligraphyRegister:   techweekWorkshopToRegister = "Caligraphy";
                                            break;
        }

        String url = "http://www.prajwalan.org/app/registerForTechweek.php";
        SendToServerUtility techweekRegisterRequest = new SendToServerUtility(url);
        techweekRegisterRequest.addFormField("email", loggedInUser.email);
        techweekRegisterRequest.addFormField("techweekWorkshop", techweekWorkshopToRegister);

        SendRegisterRequest requestForTechweek= new SendRegisterRequest();
        requestForTechweek.execute(techweekRegisterRequest);*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        processNavigationDrawer();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
    private void processNavigationDrawer() {
        NavigationDrawrerHandler.processNavigationDrawer(headerView, getApplicationContext());
    }
    public  boolean  navigation(View d) {
        if(d.getId() != R.id.nav_Techweek)
            NavigationDrawrerHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class SendRegisterRequest extends AsyncTask<SendToServerUtility,Void,String> {
        @Override
        protected String doInBackground(SendToServerUtility... params) {
            String result = "";
            try {
                SendToServerUtility loginRequest = params[0];
                result = loginRequest.send();
            }
            catch(Exception e) {
                result = "{\"success\":\"fail\",\"reason\":\"Network Problem Occurred.\"};";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String response) {
            pd.hide();
            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            try {
                JSONHandler jsonHandler = new JSONHandler(getApplicationContext());
                boolean success = jsonHandler.getSuccess(response);

                if(success) {
                    Snackbar s = Snackbar.make(iv1, "Registration Successful...", Snackbar.LENGTH_LONG).setAction("Action",null);
                    CommonUtilities.displayMessage(s);
                }
                else {
                    String reason = jsonHandler.getFailureReason(response);
                    CommonUtilities.displayAlertMessage(tweek.this,"Failed to Register",reason);
                }
            }
            catch (Exception e) {
                Snackbar s = Snackbar.make(iv1, e.toString(), Snackbar.LENGTH_LONG).setAction("Action",null);
                CommonUtilities.displayMessage(s);
            }
        }
    }
}
