package org.prajwalan.app.prajwalan;


import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;

public class Hospitality extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public TextView timers,name;
    public Handler handler,hand1;
    public Runnable runnable,run1;
    AnimatorSet se;
    private View headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitality);
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

        TextView cf =(TextView)findViewById(R.id.hospitality18);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/sf.ttf");
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality1);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality2);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality3);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality4);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality5);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality6);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality7);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality8);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality9);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality10);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality11);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality12);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality13);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality14);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality15);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality16);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality17);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.hospitality20);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.santosh);
        cf.setTypeface(face);


        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();

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
        if(d.getId() != R.id.nav_hospitality)
            NavigationDrawrerHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void call(View d) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel::8484849013"));
        // Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel"));
        // Toast.makeText(this, "Call intent is started", Toast.LENGTH_SHORT).show();
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
