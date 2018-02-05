package org.prajwalan.app.prajwalan;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
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
import android.widget.Toast;

import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;

public class sponsor_main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ImageView logo=(ImageView)findViewById(R.id.img_eve);
        logo.setImageResource(R.drawable.inlogo);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.logo_anim);
        set.setTarget(logo);
        set.start();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();
    }
    private void processNavigationDrawer() {
        NavigationDrawrerHandler.processNavigationDrawer(headerView, getApplicationContext());
    }
    public  boolean  navigation(View d) {
        if(d.getId() != R.id.nav_sponsors)
            NavigationDrawrerHandler.navigation(this, d, headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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



    public void sp(View d){
        Intent i=null;

        if (d.getId()==R.id.sp2016){
            i = new Intent(this,sponsors_2016.class);
           // Toast.makeText(getBaseContext(),"Pressed", Toast.LENGTH_LONG).show();

            startActivity(i);
        }
        if (d.getId()==R.id.sp2015){
            i = new Intent(this,sponsor.class);
           // Toast.makeText(getBaseContext(),"asdklfjkld", Toast.LENGTH_LONG).show();
            startActivity(i);
        }

        if (d.getId()==R.id.sp2017){
           // Toast.makeText(getBaseContext(),"Previoew 20117", Toast.LENGTH_LONG).show();
            i = new Intent(this,sponsors_2017.class);
            //Toast.makeText(getBaseContext(),"20117", Toast.LENGTH_LONG).show();
            startActivity(i);
        }
        if (d.getId()==R.id.sp2018){
            // Toast.makeText(getBaseContext(),"Previoew 20117", Toast.LENGTH_LONG).show();
            i = new Intent(this,sponsors2018.class);
            //Toast.makeText(getBaseContext(),"20117", Toast.LENGTH_LONG).show();
            startActivity(i);
        }

    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
