package org.prajwalan.app.prajwalan;

/**
 * Created by nadeem on 05/02/2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;

//import org.prajwalan.app.prajwalanapp.R;

public class sponsors2018 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.app_bar_sponsors_2018);
               /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
public  boolean  navigation(View d) {
        if(d.getId() != R.id.nav_sponsors)
        NavigationDrawrerHandler.navigation(this, d, headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }
        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }*/
        }
}
