package org.prajwalan.app.prajwalan;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
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

public class developer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public TextView timers,name;
    public Handler handler,hand1;
    public Runnable runnable,run1;
    AnimatorSet se;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/sf.ttf");
        TextView text=(TextView)findViewById(R.id.text);
        text.setTypeface(tf);
        ImageView logo=(ImageView)findViewById(R.id.img_eve);
        logo.setImageResource(R.drawable.inlogo);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.logo_anim);
        set.setTarget(logo);
        set.start();
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/sf.ttf");
        /*TextView tv = (TextView) findViewById(R.id.moizt);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.moizn);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.moize);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.moiza);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.app1);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.nilesht);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.nileshe);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.nileshn);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.shubhamt);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.shubhame);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.shubhamn);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.nidhit);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.nidhie);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.nidhin);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.widhit);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.widhin);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.widhie);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.app2);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.dushyantt);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.dushyantn);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.dushyante);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.ameyt);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.ameye);
        tv.setTypeface(face);
        tv = (TextView) findViewById(R.id.ameyn);
        tv.setTypeface(face);*/


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
        if(d.getId() != R.id.nav_developer)
            NavigationDrawrerHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
