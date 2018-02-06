package org.prajwalan.app.prajwalan;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;

public class Contact extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public TextView timers;
    public Handler handler,hand1;
    public Runnable runnable,run1;
    ViewPager page;

    RadioButton r1,r2,r3;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
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




    }
    public void cont(View d)
    {
        Intent i=null;
        if (d.getId()==R.id.e1){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","overall");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e15){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","faculty");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e2){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","coresupporting");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e3){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","coresponsorship");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e4){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","eventsponsorship");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e5){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","outreach");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e6){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","publicity");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e7){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","web");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e8){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","coreediting");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e10){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","coredesigning");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e14){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","networking");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e9){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","infra");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e11){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","audit");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e12){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","alumnisponsorship");
            i.putExtras(abc);
            startActivity(i);
        }
        else if (d.getId()==R.id.e13){
            i=new Intent(this,contact2.class);
            Bundle abc=new Bundle();
            abc.putString("cmtname","coreediting");
            i.putExtras(abc);
            startActivity(i);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
    private void processNavigationDrawer() {
        NavigationDrawrerHandler.processNavigationDrawer(headerView,getApplicationContext() );
    }
    public  boolean  navigation(View d) {
        if(d.getId() != R.id.nav_contact)
            NavigationDrawrerHandler.navigation(this, d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
