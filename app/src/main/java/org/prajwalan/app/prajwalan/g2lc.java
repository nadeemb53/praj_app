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

public class g2lc extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public TextView timers,name;
    public Handler handler,hand1;
    public Runnable runnable,run1;
    AnimatorSet se;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g2lc);
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
        TextView cf =(TextView)findViewById(R.id.g2lc1);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/sf.ttf");
        cf.setTypeface(face);
         cf =(TextView)findViewById(R.id.g2lc2);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.g2lc3);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.g2lc4);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.g2lc5);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.g2lc6);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.g2lc7);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.g2lc8);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.g2lc9);
        cf.setTypeface(face);
        cf =(TextView)findViewById(R.id.g2lc10);
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
   /* public  boolean  navigation(View d) {
        if(d.getId() != R.id.nav_g2lc)
            NavigationDrawrerHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
