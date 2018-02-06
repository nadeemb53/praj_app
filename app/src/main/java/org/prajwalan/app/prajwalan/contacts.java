package org.prajwalan.app.prajwalan;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.prajwalan.app.prajwalan.DB.DBContacts;
import org.prajwalan.app.prajwalan.handlers.SQLiteDbHandler;

public class contacts extends Fragment {
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.activity_contacts,container,false);

        Typeface tf=Typeface.createFromAsset(getActivity().getAssets(), "fonts/sf.ttf");

        TextView text = (TextView)rootView.findViewById(R.id.convenor);
        text.setTypeface(tf);
        text = (TextView)rootView.findViewById(R.id.coconvenor);
        text.setTypeface(tf);



        TextView dataConvenor = (TextView)rootView.findViewById(R.id.dataConvenor);
        TextView dataCoConvenor = (TextView)rootView.findViewById(R.id.dataCoConvenor);
      //  TextView mail = (TextView)rootView.findViewById(R.id.mail);

        Bundle abc = getArguments();
        String eventid = abc.getString("eventid");
        try {
            SQLiteDbHandler db = new SQLiteDbHandler(this.getContext());
            db.open();
            DBContacts[] contacts = db.getContactData(eventid);
         //  DBContacts[] data = db.getContactData("andromaster");
         //   Log.d("TAG",data[0].name);
            db.close();
            if(contacts == null ) {
                String display = "No Data Found For this Event.";
                dataConvenor.setText(display);
                dataCoConvenor.setText(display);
               // mail.setText(display);
                return rootView;
            }
            final String phone=contacts[0].mobno;
            Button button=(Button) rootView.findViewById(R.id.callcontact);
            Button button1=(Button) rootView.findViewById(R.id.whatsapp);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);
                }
            });
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Uri uri = Uri.parse("smsto:" + phone);
                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    sendIntent.setPackage("com.whatsapp");
                    Intent i = Intent.createChooser(sendIntent,"");
                    startActivity(i);
                }
            });




            //mail.setText(contacts[0].emailid);

            String display = "";
            int count = 1;
            for(int i=0;i<contacts.length;i++) {
                if(contacts[i].post.equalsIgnoreCase("convenor")) {
                    display += "" + count + ". " + contacts[i].name + "\n";
                    display += "    " + contacts[i].mobno + "\n";
                    count++;
                }
            }
            dataConvenor.setText(display);


            display = "";
            count = 1;
            for(int i=0;i<contacts.length;i++) {
                if(contacts[i].post.equalsIgnoreCase("co-convenor")) {
                    display += "" + count + ". " + contacts[i].name + "\n";
                    display += "   " + contacts[i].mobno + "\n";
                    count++;
                }
            }
            dataCoConvenor.setText(display);

        }
        catch(Exception e) {
            String display = "No Data Found For this Event.";
            dataConvenor.setText(display);
            dataCoConvenor.setText(display);
          //  mail.setText(display);

            //String display = e.toString();
           // mail.setText(display);
        }


        return  rootView;
    }



}