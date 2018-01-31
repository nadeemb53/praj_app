package org.prajwalan.app.prajwalan;

/**
 * Created by nadeem on 24/01/2018.
 */

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.prajwalan.app.prajwalan.DB.DBContacts;
import org.prajwalan.app.prajwalan.DB.DBWorkshops;
import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;
import org.prajwalan.app.prajwalan.utility.ImageUtility;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;
import org.prajwalan.app.prajwalan.utility.RoundedBitmapDrawableUtility;

public class workshop2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Resources mResources;
    private Context mContext;
    private View headerView;
    ProgressBar pb;
    ImageView iv;
    DBWorkshops workshop = null;
    DBContacts[] contacts = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();
        mResources = getResources();
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

        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();

        Bundle a = getIntent().getExtras();
        String workshopid = a.getString("workshopid");

        try {
            SQLiteDbHandler db = new SQLiteDbHandler(this);
            db.open();
            workshop = db.getWorkshopData(workshopid);
            contacts = db.getContactData(workshopid);
            if(workshop == null)
                throw new Exception();
            db.close();
        }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(),"Failed To Load Information",Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,Workshop.class);
            startActivity(i);
        }

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/sf.ttf");

        TextView tv = (TextView) findViewById(R.id.heading1);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading1);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading2);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading3);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading4);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading5);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading6);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading7);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.heading8);
        tv.setTypeface(face);

        TextView name = (TextView) findViewById(R.id.workshop_name);
        TextView desc = (TextView) findViewById(R.id.workshop_desc);
        TextView date = (TextView) findViewById(R.id.workshop_date);
        TextView duration = (TextView) findViewById(R.id.workshop_duration);
        TextView venue = (TextView) findViewById(R.id.workshop_venue);
        TextView fees = (TextView) findViewById(R.id.workshop_fees);
        TextView info = (TextView) findViewById(R.id.workshop_info);
        TextView content = (TextView) findViewById(R.id.workshop_content);
        TextView mailText = (TextView) findViewById(R.id.mailText);
        TextView mail = (TextView) findViewById(R.id.mail);
      //  TextView contact = (TextView) findViewById(R.id.workshop_contact);

        name.setTypeface(face);
        desc.setTypeface(face);
        date.setTypeface(face);
        duration.setTypeface(face);
        venue.setTypeface(face);
        fees.setTypeface(face);
        info.setTypeface(face);
        content.setTypeface(face);
        mailText.setTypeface(face);
        mail.setTypeface(face);
        //contact.setTypeface(face);

        text.setText(workshop.workshopname);
        name.setText(workshop.workshopname);
        desc.setText(workshop.workshopdesc);
        date.setText(workshop.workshopdate);
        duration.setText(workshop.workshopduration);
        venue.setText(workshop.workshopvenue);
        fees.setText(workshop.workshopfees.replace("(nl)","\n"));
        info.setText(workshop.workshopinfo.replace("(nl)","\n"));
        content.setText(workshop.workshopcontents.replace("(nl)","\n"));
      //  contact.setText(workshop.workshopcontact.replace("(nl)","\n"));
        /*if(workshop.workshopcontact == null) {
            mailText.setVisibility(View.INVISIBLE);
            mail.setVisibility(View.INVISIBLE);
            contact.setText("No Contact Information Found");
        }
        else
            contact.setText(workshop.workshopcontact);*/

        /*else {
            String display = "";
            int count = 1;

            for(int i=0;i<contacts.length;i++) {
                if(contacts[i].post.equalsIgnoreCase("convenor")) {
                    display += "" + count + ". " + contacts[i].name + "\n";
                    display += "     " + contacts[i].mobno + "\n";
                    count++;
                }
            }
            for(int i=0;i<contacts.length;i++) {
                if(contacts[i].post.equalsIgnoreCase("co-convenor")) {
                    display += "" + count + ". " + contacts[i].name + "\n";
                    display += "   " + contacts[i].mobno + "\n";
                    count++;
                }
            }
            mail.setText(contacts[0].emailid);
            Log.d("contacts : ",display);
            contact.setText(display);
        }*/


        iv = (ImageView) findViewById(R.id.workshop_image);

        pb = (ProgressBar) findViewById(R.id.load);
        RequestImageFromServer request = new RequestImageFromServer();
        request.execute(workshop);

    }
    public void call(View d) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel::+917249834052"));
        // Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel"));
        // Toast.makeText(this, "Call intent is started", Toast.LENGTH_SHORT).show();
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void processNavigationDrawer() {
        NavigationDrawrerHandler.processNavigationDrawer(headerView, getApplicationContext());
    }
    public  boolean  navigation(View d) {

            NavigationDrawrerHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class RequestImageFromServer extends AsyncTask<DBWorkshops, Void, Bitmap > {

        @Override
        protected Bitmap doInBackground(DBWorkshops... arg0) {
            Bitmap img = null;
            DBWorkshops workshop = arg0[0];
            if(ImageUtility.exists(getApplicationContext(), workshop.workshopid)) {
                img = ImageUtility.getDownloadedImage(getBaseContext(),workshop.workshopid);
            }
            else {
                try {
                    img = ImageUtility.downloadImage(workshop.imageurl);
                }catch (Exception e) {
                }
            }

            return img;
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            if(result == null) {
                Toast.makeText(getApplicationContext(),"Failed to Load Image",Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.INVISIBLE);
                return;
            }
            pb.setVisibility(View.INVISIBLE);
            RoundedBitmapDrawable drawable2 = RoundedBitmapDrawableUtility.createRoundedBitmapDrawableWithBorder(result,mResources);
            iv.setImageDrawable(drawable2);

            try {
                ImageUtility.saveImageToMemory(getApplicationContext(), workshop.workshopid, result);
            }
            catch(Exception e) {
            }
        }

    }
}
