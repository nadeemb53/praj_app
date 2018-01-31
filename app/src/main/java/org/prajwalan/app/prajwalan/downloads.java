package org.prajwalan.app.prajwalan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.prajwalan.app.prajwalan.DB.DBDownloads;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;

public class downloads extends Fragment {
    DBDownloads[] downloadData;
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.activity_download,container,false);
        final LinearLayout layout = (LinearLayout)rootView.findViewById(R.id.layout);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        Bundle abc = getArguments();
        String eventid = abc.getString("eventid");

        Typeface tf=Typeface.createFromAsset(getActivity().getAssets(), "fonts/sf.ttf");

        try {
            SQLiteDbHandler db = new SQLiteDbHandler(this.getContext());
            db.open();
            downloadData  = db.getDownloadData(eventid);
            db.close();
            if(downloadData == null ) {
                String display = "No Downloads Available for this Event.";

                TextView temp = new TextView(this.getContext());
                temp.setText(display);
                temp.setPadding(0, 40, 0, 10);
                temp.setTextColor(Color.WHITE);
                temp.setTextSize(18);
                temp.setTypeface(tf);
                layout.addView(temp, p);

                return rootView;
            }

            for(int i=0;i<downloadData.length;i++) {

                TextView temp1 = new TextView(this.getContext());
                temp1.setText(downloadData[i].description);
                temp1.setPadding(0, 50, 0, 10);
                temp1.setTextColor(Color.WHITE);
                temp1.setTextSize(17);
                temp1.setTypeface(tf);
                layout.addView(temp1, p);

                TextView temp2 = new TextView(this.getContext());
                temp2.setId(i);
                temp2.setText(downloadData[i].name);
                temp2.setTextColor(Color.WHITE);
                temp2.setTextSize(18);
                temp2.setTypeface(tf);
                temp2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        String url = downloadData[id].url;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                layout.addView(temp2, p);
            }

        }
        catch(Exception e) {
            String display = "No Downloads Available for this Event.";
            TextView temp = new TextView(this.getContext());
            temp.setPadding(0, 40, 0, 10);
            temp.setTextColor(Color.WHITE);
            temp.setTextSize(18);
            temp.setText(display);
            temp.setTypeface(tf);
            layout.addView(temp, p);
        }

        return  rootView;
    }

}

