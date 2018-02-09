package org.prajwalan.app.prajwalan;


import com.pushwizard.sdk.PushWizard;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

//import com.pushwizard.sdk.*;

import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;
import org.prajwalan.app.prajwalan.DB.DBUpdates;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public TextView timers, name;
    public Handler handler, hand1;
    public Runnable runnable, run1;
    private Boolean exit = false;
    public TextView text;
    AnimatorSet se;

    //private static final String PUSHWIZARD_APPKEY = "56c2f1bea3fc27df3f8b459d";
    //private static final String GOOGLE_PROJECT_ID = "783483290495";


    private ViewFlipper viewFlipper;
    private float lastX;

    TextView tv;
    Intent i = null;

    AnimatorSet set;
    ImageView img1, img2, img3, img4, img5, img6, logo;


    CollapsingToolbarLayout collapse;

    ViewPager view;

    private Typeface normalFont;
    private Typeface boldFont;
    Handler handle;
    Runnable runn;


    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapse = (CollapsingToolbarLayout) findViewById(R.id.collapsing_container);
        setSupportActionBar(toolbar);


        // Foreground process get start ....and start forever...
        BackgroundTasksUtilities.scheduleForegroundServices(getApplicationContext());



        Typeface ty = Typeface.createFromAsset(getAssets(), "fonts/sf.ttf");
        text = (TextView) findViewById(R.id.app_text);
        text.setTypeface(ty);
        TextView marquee = (TextView) findViewById(R.id.update);
        marquee.setSelected(true);
        logo = (ImageView) findViewById(R.id.icon_id);
        logo.setImageResource(R.drawable.inlogo);
        set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.logo_anim);
        set.setTarget(logo);
        set.start();

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {

                    text.setVisibility(View.VISIBLE);
                    logo.setVisibility(View.VISIBLE);

                    isShow = true;
                } else if (isShow) {
                    logo.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);

                    isShow = false;
                }
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);

        processNavigationDrawer();

        displayUpdates();

       /* view = (ViewPager) findViewById(R.id.view);
        view.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));


        handle = new Handler();
        runn = new Runnable() {
            public void run() {
                int id = view.getCurrentItem();
                handle.postDelayed(runn, 4000);
                if (id >= 1)
                    id = 0;
                else id = id + 1;
                view.setCurrentItem(id, true);
            }
        };
        handle.postDelayed(runn, 4000);*/

    }

    public void displayUpdates() {
        TextView update = (TextView) findViewById(R.id.update);
        DBUpdates[] updates = null;
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(this);
            db.open();
            updates = db.getAllUpdatesData();
            db.close();

            if (updates == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            update.setText("Prajwalan is Back with a Bang with new Events and Technical Fun.");
            return;
        }

        String display = "";
        for (int i = 0; i < updates.length; i++)
            display += updates[i].updates;
        update.setText(display);

    }


    public void process(View v) {

        if (v.getId() == R.id.events) {
            i = new Intent(this, Events.class);
            startActivity(i);
        }
        if (v.getId() == R.id.workshops) {
            i = new Intent(this, Workshop.class);
            startActivity(i);
        }
        if (v.getId() == R.id.social) {
            i = new Intent(this, Social.class);
            startActivity(i);
        }
        if (v.getId() == R.id.techweek) {
            i = new Intent(this, tweek.class);
            startActivity(i);
        }
        /*if(v.getId()==R.id.trailer)
        {

        }*/
        if (v.getId() == R.id.contacts) {
            i = new Intent(this, Contact.class);
            startActivity(i);
        }
        if (v.getId() == R.id.sponsors) {
            i = new Intent(this, sponsor_main.class);
            startActivity(i);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Are you sure?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("no", null).show();
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        processNavigationDrawer();
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        PushWizard.launchPushWizard(this,
                GOOGLE_PROJECT_ID, PUSHWIZARD_APPKEY ,
                null,
                "start",
                PushWizard.GEOLOCATION_PROCESSING_MODE.OFF

        );

    }*/

    @Override
    protected void onPause() {
        super.onPause();

        PushWizard.handleSession(null, "end", PushWizard.GEOLOCATION_PROCESSING_MODE.OFF);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    private void processNavigationDrawer() {
        NavigationDrawrerHandler.processNavigationDrawer(headerView, getApplicationContext());

    }

    public boolean navigation(View d) {
        if (d.getId() != R.id.nav_home)
            NavigationDrawrerHandler.navigation(this, d, headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int[] mResource = {
                R.drawable.trailer,
                R.drawable.ola
        };

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment tab = new ViewPagerFragment();
            Bundle b = new Bundle();
            b.putInt("resource", mResource[position]);
            b.putInt("position", position);
            tab.setArguments(b);
            return tab;
        }

        @Override
        public int getCount() {
            return mResource.length;
        }
    }

    public static class ViewPagerFragment extends Fragment {


        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.viewpageritem, container, false);

            Bundle b = getArguments();
            int resource = b.getInt("resource");
            int position = b.getInt("position");

            ImageView iv = (ImageView) rootView.findViewById(R.id.viewpageritem);

            iv.setImageResource(resource);

            if (position == 0) {
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String video_path = "https://www.youtube.com/watch?v=QOGT1TMtSM0";
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(video_path));
                        startActivity(i);
                    }
                });
            }
            if (position == 1) {
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = null;

                        try {
                            intent = new Intent("com.olacabs.customer");
                            // get the Twitter app if possible
                            //getContext().getPackageManager().getPackageInfo("com.olacabs.customer", 0);
                            //intent = new Intent(Intent.ACTION_VIEW);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        } catch (Exception e) {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.olacabs.com/"));
                        }
                        startActivity(intent);
                    }
                });
            }

            return rootView;
        }


    }
}


