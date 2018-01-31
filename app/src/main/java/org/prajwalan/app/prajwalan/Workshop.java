package org.prajwalan.app.prajwalan;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.AsyncTask;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.prajwalan.app.prajwalan.DB.DBWorkshops;
import org.prajwalan.app.prajwalan.handlers.NavigationDrawrerHandler;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;
import org.prajwalan.app.prajwalan.utility.ImageUtility;
import org.prajwalan.app.prajwalan.workshop.WorkshopAdapter;
import org.prajwalan.app.prajwalan.workshop.WorkshopItem;

import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class Workshop extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public TextView timers;
    public Handler handler;
    public Runnable runnable;
    private Context mContext;
    private Resources mResources;

    private List<WorkshopItem> workshopList;
    private View headerView;

    ListView listView;
    WorkshopAdapter workshopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_workshop);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            mContext = getApplicationContext();
            Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/sf.ttf");
            TextView text=(TextView)findViewById(R.id.text);
            text.setTypeface(tf);
            ImageView logo=(ImageView)findViewById(R.id.img_eve);
            logo.setImageResource(R.drawable.inlogo);
            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.logo_anim);
            set.setTarget(logo);
            set.start();

            mResources = getResources();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
            processNavigationDrawer();

            listView = (ListView)findViewById(R.id.WorkshopListView);

            workshopList = new Vector<WorkshopItem>();

            workshopAdapter = new WorkshopAdapter(getApplicationContext(),workshopList, getLayoutInflater());
            listView.setAdapter(workshopAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    WorkshopItem itemClicked = (WorkshopItem) parent.getAdapter().getItem(position);
                    Bundle a = new Bundle();
                    a.putString("workshopid", itemClicked.workshop_id);
                    Intent i = new Intent(Workshop.this,workshop2.class);
                    i.putExtras(a);
                    startActivity(i);

                }
            });

            addWorkshopList(mResources);
        }
        catch (Exception e) {
            //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    private void addWorkshopList(Resources resources) {
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(this);
            db.open();
            DBWorkshops workshops[] = db.getAllWorkshopData();
            db.close();
            for(int k=0; k<workshops.length; k++) {
                RequestImageFromServer request = new RequestImageFromServer();
                request.execute(workshops[k]);
                workshopList.add(new WorkshopItem(workshops[k].workshopid,workshops[k].workshopname, workshops[k].workshopdesc, resources.getDrawable(R.drawable.workshop)));
                workshopAdapter.notifyDataSetChanged();
            }
        }
        catch(Exception e) {
            //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
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
        borderPaint.setStrokeWidth(borderWidthHalf*2);
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
    public  boolean  navigation(View d) {
        if(d.getId() != R.id.nav_Workshop)
            NavigationDrawrerHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class RequestImageFromServer extends AsyncTask<DBWorkshops, Void, Bitmap > {

        DBWorkshops workshop;

        @Override
        protected Bitmap doInBackground(DBWorkshops... arg0) {
            Bitmap img = null;
            workshop = arg0[0];

            if(ImageUtility.exists(getApplicationContext(), workshop.workshopid)) {
                img = ImageUtility.getDownloadedImage(getBaseContext(),workshop.workshopid);
            }
            else {
                try {
                    img = ImageUtility.downloadImage(workshop.imageurl);
                }catch (Exception e) {
                }
            }

            return img;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if(result == null) {
                return;
            }

            ListIterator<WorkshopItem> litr = workshopList.listIterator();
            while(litr.hasNext()) {
                WorkshopItem w = litr.next();
                if(w.workshop_id == workshop.workshopid) {
                    w.workshop_image = createRoundedBitmapDrawableWithBorder(result);
                    //litr.set(w);
                    workshopAdapter.notifyDataSetChanged();
                }
            }

            try {
                ImageUtility.saveImageToMemory(getApplicationContext(), workshop.workshopid, result);
            }
            catch(Exception e) {
            }
        }

    }


}
