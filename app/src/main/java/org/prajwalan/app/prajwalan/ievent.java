package org.prajwalan.app.prajwalan;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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
import org.prajwalan.app.prajwalan.utility.RoundedBitmapDrawableUtility;

public class ievent extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public TextView timers,name;
    private Bitmap bit1;
    private ImageView iv1;
    public Handler handler,hand1;
    public Runnable runnable,run1;
    private Resources mResources;
    AnimatorSet se;
    private View headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ievent);
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

        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();

        mResources = getResources();

        /*iv1=(ImageView)findViewById(R.id.greeting);
        bit1= BitmapFactory.decodeResource(mResources, R.drawable.greeting);
        RoundedBitmapDrawable drawable2 = RoundedBitmapDrawableUtility.createRoundedBitmapDrawableWithBorder(bit1, mResources);
        iv1.setImageDrawable(drawable2);
        iv1=(ImageView)findViewById(R.id.web);
        bit1= BitmapFactory.decodeResource(mResources, R.drawable.webdesigning);
        RoundedBitmapDrawable drawable1 = RoundedBitmapDrawableUtility.createRoundedBitmapDrawableWithBorder(bit1, mResources);
        iv1.setImageDrawable(drawable1);
        iv1=(ImageView)findViewById(R.id.smart);
        bit1= BitmapFactory.decodeResource(mResources, R.drawable.smartcity);
        RoundedBitmapDrawable drawable3 = RoundedBitmapDrawableUtility.createRoundedBitmapDrawableWithBorder(bit1, mResources);
        iv1.setImageDrawable(drawable3);
        iv1=(ImageView)findViewById(R.id.master);
        bit1= BitmapFactory.decodeResource(mResources, R.drawable.mastermind1);
        RoundedBitmapDrawable drawable4 = RoundedBitmapDrawableUtility.createRoundedBitmapDrawableWithBorder(bit1, mResources);
        iv1.setImageDrawable(drawable4);*/



    }
    private RoundedBitmapDrawable createRoundedBitmapDrawableWithBorder(Bitmap bitmap){
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int borderWidthHalf = 10; // In pixels

        int bitmapRadius = Math.min(bitmapWidth,bitmapHeight)/2;

        int bitmapSquareWidth = Math.min(bitmapWidth,bitmapHeight);


        int newBitmapSquareWidth = bitmapSquareWidth+borderWidthHalf;
        Bitmap roundedBitmap = Bitmap.createBitmap(newBitmapSquareWidth,newBitmapSquareWidth,Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas(roundedBitmap);

        canvas.drawColor(Color.RED);

        int x = borderWidthHalf + bitmapSquareWidth - bitmapWidth;
        int y = borderWidthHalf + bitmapSquareWidth - bitmapHeight;


        canvas.drawBitmap(bitmap, x, y, null);

        // Initializing a new Paint instance to draw circular border
        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidthHalf * 2);
        borderPaint.setColor(Color.WHITE);


        canvas.drawCircle(canvas.getWidth() / 2, canvas.getWidth() / 2, newBitmapSquareWidth / 2, borderPaint);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mResources, roundedBitmap);

        roundedBitmapDrawable.setCornerRadius(bitmapRadius);

        roundedBitmapDrawable.setAntiAlias(true);

        return roundedBitmapDrawable;
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
    public void idea(View v)
    {
        Intent i=null;

         if(v.getId()==R.id.equitz)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","equitz");
            i.putExtras(abc);
            startActivity(i);
        }
        else if(v.getId()==R.id.letstalk)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","letstalk");
            i.putExtras(abc);
            startActivity(i);
        }
         else if(v.getId()==R.id.techshetra)
         {
             i=new Intent(this,Robosoccer.class);
             Bundle abc=new Bundle();
             abc.putString("eventid","techshetra");
             i.putExtras(abc);
             startActivity(i);
         }else if(v.getId()==R.id.smartcity)
         {
             i=new Intent(this,Robosoccer.class);
             Bundle abc=new Bundle();
             abc.putString("eventid","smartcity");
             i.putExtras(abc);
             startActivity(i);
         }else if(v.getId()==R.id.editmania)
         {
             i=new Intent(this,Robosoccer.class);
             Bundle abc=new Bundle();
             abc.putString("eventid","editmania");
             i.putExtras(abc);
             startActivity(i);
         }else if(v.getId()==R.id.iplauction)
         {
             i=new Intent(this,Robosoccer.class);
             Bundle abc=new Bundle();
             abc.putString("eventid","iplauction");
             i.putExtras(abc);
             startActivity(i);
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
        return true;
    }
    private void processNavigationDrawer() {
        NavigationDrawrerHandler.processNavigationDrawer(headerView, getApplicationContext());
    }
    public  boolean  navigation(View d) {
        if(d.getId() != R.id.nav_myidea)
            NavigationDrawrerHandler.navigation(this, d, headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
