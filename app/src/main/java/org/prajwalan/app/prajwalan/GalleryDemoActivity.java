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
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;


public class GalleryDemoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public TextView timers;
    public Handler handler,hand1;
    public Runnable runnable,run1;
    ImageView selectedImage;
    private Integer[] mImageIds = {
           /* R.drawable.natureimage1,
            R.drawable.natureimage2,
            R.drawable.natureimage3,
            R.drawable.natureimage4,
            R.drawable.natureimage5,
            R.drawable.natureimage6,
            R.drawable.natureimage7,
            R.drawable.natureimage8,
            R.drawable.natureimage9,
            R.drawable.natureimage10*/
            R.drawable.nb1, R.drawable.nb2, R.drawable.nb3, R.drawable.nb4, R.drawable.nb5, R.drawable.nb6, R.drawable.nb7,
            R.drawable.nb8, R.drawable.nb9, R.drawable.nb10, R.drawable.nb11, R.drawable.nb12, R.drawable.nb13, R.drawable.nb14
           // R.drawable.nb15, R.drawable.nb16, R.drawable.nb17, R.drawable.nb18, R.drawable.nb19, R.drawable.nb20, R.drawable.nb21,
           // R.drawable.nb22, R.drawable.nb23, R.drawable.nb24, R.drawable.nb25

    };
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_demo);
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

        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        selectedImage=(ImageView)findViewById(R.id.imageView1);
        gallery.setSpacing(1);
        gallery.setAdapter(new GalleryImageAdapter(this));

        // clicklistener for Gallery
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // show the selected Image
                selectedImage.setImageResource(mImageIds[position]);
            }
        });

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
        if(d.getId() != R.id.nav_gallary)
            NavigationDrawrerHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
