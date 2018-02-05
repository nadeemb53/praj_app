package org.prajwalan.app.prajwalan.handlers;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.prajwalan.app.prajwalan.About;
import org.prajwalan.app.prajwalan.Contact;
import org.prajwalan.app.prajwalan.DB.User;
import org.prajwalan.app.prajwalan.Register_Activity;
import org.prajwalan.app.prajwalan.Workshop;
import org.prajwalan.app.prajwalan.Events;
import org.prajwalan.app.prajwalan.GalleryDemoActivity;
import org.prajwalan.app.prajwalan.Hospitality;
import org.prajwalan.app.prajwalan.MainActivity;
import org.prajwalan.app.prajwalan.R;

import org.prajwalan.app.prajwalan.Social;
import org.prajwalan.app.prajwalan.Tshirt;
import org.prajwalan.app.prajwalan.developer;

import org.prajwalan.app.prajwalan.ievent;
import org.prajwalan.app.prajwalan.junior;
import org.prajwalan.app.prajwalan.principal;
import org.prajwalan.app.prajwalan.socialcontest;
import org.prajwalan.app.prajwalan.sponsor_main;
import org.prajwalan.app.prajwalan.staff;
import org.prajwalan.app.prajwalan.ThemeSongs;
import org.prajwalan.app.prajwalan.tedx;
import org.prajwalan.app.prajwalan.tweek;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nadeem on 26/01/2018.
 */
public class NavigationDrawrerHandler extends AppCompatActivity {

    public void tedx(Context context,View view){
        Intent intent = new Intent(context , tedx.class);
        context.startActivity(intent);

    }

    public static boolean  navigation(Context context,View d,View headerView) {
        Intent i;

        switch (d.getId()) {
            case R.id.nav_home :                i = new Intent(context, MainActivity.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_events :              i = new Intent(context, Events.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_Workshop :            i = new Intent(context, Workshop.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_Initiative :          i = new Intent(context, Social.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_Techweek :            i = new Intent(context, tweek.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_contest :             i = new Intent(context, socialcontest.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_myidea :              i = new Intent(context, ievent.class);
                                                context.startActivity(i);
                                                break;

            case R.id.nav_theme_song :             i = new Intent(context, ThemeSongs.class);
                                                context.startActivity(i);
                                                 break;
            case R.id.nav_shirt :               i = new Intent(context, Tshirt.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_princi :               i = new Intent(context, principal.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_gallary :             i = new Intent(context, GalleryDemoActivity.class);
                                                context.startActivity(i);
                                                break;
           // case R.id.nav_g2lc :                i = new Intent(context, g2lc.class);
             //                                   context.startActivity(i);
               //                                 break;
           /* case R.id.nav_staff :                i = new Intent(context, staff.class);
                                                context.startActivity(i);
                                                 break;
           */ case R.id.nav_about :               i = new Intent(context, About.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_contact :             i = new Intent(context, Contact.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_hospitality :         i = new Intent(context, Hospitality.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_sponsors :             i = new Intent(context, sponsor_main.class);
                                                context.startActivity(i);
                                                 break;
            case R.id.nav_tedx :                i = new Intent(context, tedx.class);
                                                context.startActivity(i);
                                                 break;
            case R.id.nav_pcup :                i = new Intent(context, staff.class);
                                                context.startActivity(i);
                                                break;
            case R.id.nav_jrpcup :                i = new Intent(context, junior.class);
                                                context.startActivity(i);
                                                 break;
            case R.id.nav_developer :           i = new Intent(context, developer.class);
                                                context.startActivity(i);
                                                break;
            case R.id.iwhatt :                  String number = "8605638967";
                Uri uri = Uri.parse("smsto:" + number);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                sendIntent.setPackage("com.whatsapp");
                i = Intent.createChooser(sendIntent,"");
                context.startActivity(i);
                break;
            case R.id.fb :                       Intent intent=null;

                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/prajwalan.gcoea")));

                break;
            case R.id.iinsta :                  uri = Uri.parse("https://www.instagram.com/prajwalan.techfest");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    context.startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/prajwalan.techfest")));
                }
                break;
            case R.id.itwit :
                try {
                    // get the Twitter app if possible
                    context.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=2589242826"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/prajwalan5"));
                }
                context.startActivity(intent);
                break;
            case R.id.iyt :                     String video_path = "https://www.youtube.com/channel/UCGFvFsIlJsn_YQY4Q-pK5_Q";
                i = new Intent(Intent.ACTION_VIEW, Uri.parse(video_path));
                context.startActivity(i);
                break;
            case R.id.ilink :                   try {
                Intent linkedinIntent = new Intent(Intent.ACTION_VIEW);
                linkedinIntent.setClassName("com.linkedin.android", "com.linkedin.android.profile.ViewProfileActivity");
                linkedinIntent.putExtra("memberId","AAkAABv9WnsBwCg1qWcMGrkAKp1VSBNDK-W2WqI");
                context.startActivity(linkedinIntent);
            } catch (Exception e) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/profile/view?id=AAkAABv9WnsBwCg1qWcMGrkAKp1VSBNDK-W2WqI&authType=NAME_SEARCH&authToken=O4CD&locale=en_US&trk=tyah&trkInfo=clickedVertical%3Amynetwork%2CclickedEntityId%3A469588603%2CauthType%3ANAME_SEARCH%2Cidx%3A1-1-1%2CtarId%3A1452623990306%2Ctas%3Aprajwalan"));
                context. startActivity(intent);
            }
                break;
            case R.id.nav_url1 :                String url = "http://www.prajwalan.org";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
                break;

        }

        return true;
    }

    public static void processNavigationDrawer(View headerView, Context c) {
      /*  setLoginHeaderData(headerView,c);*/
        startCountDown(headerView);
        setCustomFont(headerView, c);
    }

    private static void startCountDown(View headerView) {
        final Handler handler = new Handler();
        final TextView timers = (TextView) headerView.findViewById(R.id.timer);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date futureDate = dateFormat.parse("2018-02-27 10:00:00");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        timers.setText("" + String.format("%d:%02d:%02d:%02d", days,hours,minutes,seconds));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private static void setCustomFont(View headerView,Context c) {

        Typeface face = Typeface.createFromAsset(c.getAssets(), "fonts/sf.ttf");

        TextView tv = (TextView) headerView.findViewById(R.id.nav_home);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_events);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_theme_song);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_Workshop);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_Initiative);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_Techweek);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_contest);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_myidea);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_shirt);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_gallary);
        tv.setTypeface(face);
       /* tv = (TextView) headerView.findViewById(R.id.nav_g2lc);
        tv.setTypeface(face);*/
        tv = (TextView) headerView.findViewById(R.id.nav_about);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_contact);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_tedx);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_hospitality);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_developer);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_sponsors);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_princi);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_pcup);
        tv.setTypeface(face);
        tv = (TextView) headerView.findViewById(R.id.nav_jrpcup);
        tv.setTypeface(face);
    }

  /*  private static void setLoginHeaderData(View headerView,Context c) {

        LinearLayout notLoggedInLayout = (LinearLayout) headerView.findViewById(R.id.notLoggedInLayout);
        LinearLayout loggedInLayout = (LinearLayout) headerView.findViewById(R.id.loggedInLayout);

        User loggedInUser = SharedPreferencesHandler.getStoredUserDetails(c);

        if(loggedInUser == null) {
            notLoggedInLayout.setVisibility(View.VISIBLE);
            loggedInLayout.setVisibility(View.INVISIBLE);
            return;
        }
        else {
            notLoggedInLayout.setVisibility(View.INVISIBLE);
            loggedInLayout.setVisibility(View.VISIBLE);

            TextView name = (TextView) headerView.findViewById(R.id.name);
            TextView surname = (TextView) headerView.findViewById(R.id.lastname);
            TextView email = (TextView) headerView.findViewById(R.id.email);

            name.setText(loggedInUser.fname);
            surname.setText(loggedInUser.lname);
            email.setText(loggedInUser.email);
        }

    }

    private static void logout(final Context context, final View headerView) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setMessage("Are you sure you want to logout");
        alertBuilder.setPositiveButton("Yes", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            SharedPreferencesHandler.clearStoredUserDetails(context);
            Toast.makeText(context, "Logged Out Successfully..", Toast.LENGTH_LONG).show();
            setLoginHeaderData(headerView,context);
            }
        });
        alertBuilder.setNegativeButton("Cancel", null);

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.setTitle("Confirm ?");
        alertDialog.show();
    }*/
}
