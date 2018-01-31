package org.prajwalan.app.prajwalan;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.prajwalan.app.prajwalan.DB.DBEvents;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;

public class information extends Fragment {
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.activity_information,container,false);

        TextView text = (TextView)rootView.findViewById(R.id.data);
        TextView fees = (TextView)rootView.findViewById(R.id.fees);
        TextView feesValue = (TextView)rootView.findViewById(R.id.fees_value);
        Typeface tf=Typeface.createFromAsset(getActivity().getAssets(),"fonts/sf.ttf");
        text.setTypeface(tf);
        fees.setTypeface(tf);
        feesValue.setTypeface(tf);

        try {

            Bundle abc = getArguments();
            String eventid = abc.getString("eventid");

            SQLiteDbHandler db = new SQLiteDbHandler(this.getContext());
            db.open();
            DBEvents event = db.getEventData(eventid);
            db.close();
            if(event == null ) {
                String display = "No Data Found For this Event.";
                text.setText(display);
                fees.setVisibility(View.INVISIBLE);
                return rootView;
            }
            String display = event.eventinfo ;
            display = display.replace("(nl)","\n");
            text.setText(display);
            display = event.eventfees ;
            display = display.replace("(nl)","\n");
            feesValue.setText(display);
        }
        catch(Exception e) {
            String display = "No Data Found For this Event.";
            text.setText(display);
            fees.setVisibility(View.INVISIBLE);
        }


        return  rootView;
    }
}
