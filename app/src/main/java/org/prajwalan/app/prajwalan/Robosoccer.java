package org.prajwalan.app.prajwalan;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.prajwalan.app.prajwalan.DB.DBEvents;
import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;
import org.prajwalan.app.prajwalan.utility.ImageUtility;



import java.util.GregorianCalendar;

public class Robosoccer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent i=null;
    private View headerView;
    DBEvents event;
    ProgressBar pb;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robosoccer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle abc=getIntent().getExtras();
        String eventid=abc.getString("eventid");

        Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/sf.ttf");
        TextView text=(TextView)findViewById(R.id.text);
        text.setTypeface(tf);
        ImageView logo=(ImageView)findViewById(R.id.img_eve);
        logo.setImageResource(R.drawable.inlogo);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.logo_anim);
        set.setTarget(logo);
        set.start();

        try {
            SQLiteDbHandler db = new SQLiteDbHandler(this);
            db.open();
            event = db.getEventData(eventid);
            db.close();
            text.setText(event.eventname);
        }
        catch (Exception e) {
            event = null;
            text.setText("EVENTS");
        }

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Rules"));
        tabLayout.addTab(tabLayout.newTab().setText("Download"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));
        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(),eventid);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();

        //download EVENT IMAGE
        pb = (ProgressBar) findViewById(R.id.load);
        iv = (ImageView) findViewById(R.id.eventimg);
        RequestImageFromServer request = new RequestImageFromServer();
        request.execute(event);

    }

        public void rem(View v) {
        if(v.getId()==R.id.sr) {
            Intent calIntent = new Intent(Intent.ACTION_INSERT);
            calIntent.setType("vnd.android.cursor.item/event");
            calIntent.putExtra(CalendarContract.Events.TITLE,event.eventname);
            calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "GCOE Amravati");
            calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "");
            GregorianCalendar calDate = new GregorianCalendar(2017, 2, 9);
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.getTimeInMillis());
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.getTimeInMillis());
            startActivity(calIntent);
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
        return false;
    }
    private void processNavigationDrawer() {
        NavigationDrawrerHandler.processNavigationDrawer(headerView,getApplicationContext() );
    }
    public boolean navigation(View d) {
        NavigationDrawrerHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class RequestImageFromServer extends AsyncTask<DBEvents, Void, Bitmap > {

        @Override
        protected Bitmap doInBackground(DBEvents... arg0) {
            Bitmap img = null;
            DBEvents event = arg0[0];
            if(event == null) {

            }
            else if(ImageUtility.exists(getApplicationContext(),event.eventid)) {
                img = ImageUtility.getDownloadedImage(getBaseContext(),event.eventid);
            }
            else {
                try {
                    img = ImageUtility.downloadImage(event.imageurl);
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
            iv.setImageBitmap(result);

            try {
                ImageUtility.saveImageToMemory(getApplicationContext(), event.eventid, result);
            }
            catch(Exception e) {
            }
        }

    }
}
