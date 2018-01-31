package org.prajwalan.app.prajwalan;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;

public class contact2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle abc=getIntent().getExtras();
        String cmtname=abc.getString("cmtname");
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/sf.ttf");
        TextView text=(TextView)findViewById(R.id.text);
        text.setTypeface(tf);

        ImageView iv = (ImageView)findViewById(R.id.contact);

        if(cmtname.equalsIgnoreCase("publicity")) {
            iv.setBackground(getResources().getDrawable(R.drawable.publicity1));

            text.setText("PUBLICITY");
        }
        else  if(cmtname.equalsIgnoreCase("overall")) {
           iv.setBackground(getResources().getDrawable(R.drawable.overall1));

            text.setText("OVERALL");
        }
        else  if(cmtname.equalsIgnoreCase("faculty")) {
            iv.setBackground(getResources().getDrawable(R.drawable.pachghare));

            text.setText("Faculty Incharge");
        }
        else  if(cmtname.equalsIgnoreCase("coresupporting")) {
            iv.setBackground(getResources().getDrawable(R.drawable.coresupp1));

            text.setText("CORE SUPPORTING");
        }else  if(cmtname.equalsIgnoreCase("coresponsorship")) {
            iv.setBackground(getResources().getDrawable(R.drawable.corespon1));

            text.setText("CORE SPONSORSHIP");
        }else  if(cmtname.equalsIgnoreCase("eventsponsorship")) {
            iv.setBackground(getResources().getDrawable(R.drawable.eventspon1));

            text.setText("EVENT SPONSORSHIP");
        }else  if(cmtname.equalsIgnoreCase("outreach")) {
            iv.setBackground(getResources().getDrawable(R.drawable.outreach1));

            text.setText("OUTREACH");
        }else  if(cmtname.equalsIgnoreCase("web")) {
            iv.setBackground(getResources().getDrawable(R.drawable.web1));

            text.setText("WEB & APP DESIGNING");
        }else  if(cmtname.equalsIgnoreCase("networking")) {
            iv.setBackground(getResources().getDrawable(R.drawable.networking1));

            text.setText("NETWORKING");
        }
        else  if(cmtname.equalsIgnoreCase("infra")) {
            iv.setBackground(getResources().getDrawable(R.drawable.infra1));

            text.setText("INFRASTRUCTURE");
        }else  if(cmtname.equalsIgnoreCase("audit")) {
           iv.setBackground(getResources().getDrawable(R.drawable.audit1));

            text.setText("AUDIT");
        }else  if(cmtname.equalsIgnoreCase("alumnisponsorship")) {
            iv.setBackground(getResources().getDrawable(R.drawable.alumini1));

            text.setText("ALUMINI SPONSORSHIP");
        }
        else  if(cmtname.equalsIgnoreCase("coredesigning")) {
            iv.setBackground(getResources().getDrawable(R.drawable.coredes1));

            text.setText("Core Designing");
        }
        else  if(cmtname.equalsIgnoreCase("coreediting")) {
            iv.setBackground(getResources().getDrawable(R.drawable.coreeditting));

            text.setText("Core Editing");
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();
        ImageView logo=(ImageView)findViewById(R.id.img_eve);
        logo.setImageResource(R.drawable.inlogo);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.logo_anim);
        set.setTarget(logo);
        set.start();
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
    private void processNavigationDrawer() {
        NavigationDrawrerHandler.processNavigationDrawer(headerView, getApplicationContext());
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public  boolean  navigation(View d) {

            NavigationDrawrerHandler.navigation(this, d, headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
