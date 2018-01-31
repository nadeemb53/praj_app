package org.prajwalan.app.prajwalan;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.prajwalan.app.prajwalan.DB.DBRules;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;

public class Rules extends Fragment {
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.activity_rules,container,false);

        TextView text = (TextView)rootView.findViewById(R.id.data);
        Typeface tf=Typeface.createFromAsset(getActivity().getAssets(),"fonts/sf.ttf");
        text.setTypeface(tf);

        Bundle abc = getArguments();
        String eventid = abc.getString("eventid");

        try {
            SQLiteDbHandler db = new SQLiteDbHandler(this.getContext());
            db.open();
            DBRules[] rules = db.getRuleData(eventid);
            db.close();
            if(rules == null ) {
                String display = "No Data Found For this Event.";
                text.setText(display);
                return rootView;
            }
            String display = "";
            for(int i=0;i<rules.length;i++) {
                String rule = rules[i].rule.replace("(nl)","\n");
                display += "" + (i+1) + ". " + rule + "\n";
            }
            text.setText(display);
        }
        catch(Exception e) {
            String display = "No Data Found For this Event.";
            text.setText(e.toString());
        }


        return  rootView;
    }



}