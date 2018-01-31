package com.example.nilesh.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.io.InputStream;

public class intnentdata extends AppCompatActivity {
    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intnentdata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        et1= (EditText) findViewById(R.id.etdata);
        et2= (EditText) findViewById(R.id.etsec);
    }
    public void inten(View v){
        Intent i;
        String fname,lname;
        if(v.getId()==R.id.btdataintne){
            fname=et1.getText().toString();
            lname=et2.getText().toString();
            i=new Intent(this,intentdatareceiver.class);
            i.putExtra("First",fname);
            i.putExtra("Second",lname);
            startActivity(i);
        }


    }

}
