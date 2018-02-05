package org.prajwalan.app.prajwalan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//import org.prajwalan.app.prajwalanapp.R;

public class junior extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junior);
    }
    public void idea(View v)
    {
        Intent i=null;

        if(v.getId()==R.id.jrrobotics)
        {
            i=new Intent(this,Robotics.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","robotics");
            i.putExtras(abc);
            startActivity(i);
        }
        else if(v.getId()==R.id.jrchanakya)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","chanakya");
            i.putExtras(abc);
            startActivity(i);
        }
        else if(v.getId()==R.id.jrmodelexhibition)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","modelexhibition");
            i.putExtras(abc);
            startActivity(i);
        }else if(v.getId()==R.id.jrletstalk)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","letstalk");
            i.putExtras(abc);
            startActivity(i);
        }else if(v.getId()==R.id.jrrubicscube)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","rubicscube");
            i.putExtras(abc);
            startActivity(i);
        }else if(v.getId()==R.id.jrangrybirds)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","angrybirds");
            i.putExtras(abc);
            startActivity(i);
        }
        else if(v.getId()==R.id.jrboxcricket)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","boxcricket");
            i.putExtras(abc);
            startActivity(i);
        }
        else if(v.getId()==R.id.jrlanbattle)
        {
            i=new Intent(this,Robosoccer.class);
            Bundle abc=new Bundle();
            abc.putString("eventid","lanbattle");
            i.putExtras(abc);
            startActivity(i);
        }

    }
}
