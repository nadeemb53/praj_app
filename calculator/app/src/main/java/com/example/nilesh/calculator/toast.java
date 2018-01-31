package com.example.nilesh.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class toast extends AppCompatActivity {
    EditText nametext,agetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        nametext = (EditText) findViewById(R.id.namet);
        agetext= (EditText) findViewById(R.id.aget);
    }
    public void showData(View v){
        if(v.getId()==R.id.toast);
        String name,age;
        name =nametext.getText().toString();
        age = agetext.getText().toString();
        Toast.makeText(this,name+"\n"+age,Toast.LENGTH_LONG).show();
    }
}
