package com.example.nilesh.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    TextView tx;
    Button bt1,bt2,bt3,bt4,bt5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.no1);
        et2= (EditText) findViewById(R.id.no2);
        tx= (TextView) findViewById(R.id.ans);
       /* bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        bt3= (Button) findViewById(R.id.bt3);
        bt4= (Button) findViewById(R.id.bt4);
        bt5= (Button) findViewById(R.id.bt5);*/
    }
    public void operation(View view){
        int a,b;
        float res=0;
        Intent i=null;

        if(view.getId()==R.id.bt1){
            a=Integer.parseInt(et1.getText().toString());
            b=Integer.parseInt(et2.getText().toString());
            res=(float) a+b;
        }
        if(view.getId()==R.id.bt2){
            a=Integer.parseInt(et1.getText().toString());
            b=Integer.parseInt(et2.getText().toString());
            res=(float) a-b;
        }
        if(view.getId()==R.id.bt3){
            a=Integer.parseInt(et1.getText().toString());
            b=Integer.parseInt(et2.getText().toString());
            res=(float) a/b;
        }
        if(view.getId()==R.id.bt4){
            a=Integer.parseInt(et1.getText().toString());
            b=Integer.parseInt(et2.getText().toString());
            res=(float) a*b;
        }
        if(view.getId()==R.id.bt5){
            a=Integer.parseInt(et1.getText().toString());
            b=Integer.parseInt(et2.getText().toString());
            res=(float) a%b;        }
        tx.setText(String.valueOf(res));
        if(view.getId()==R.id.bt6){
            finish();
        }
        if(view.getId()==R.id.next){
             i=new Intent(this, toast.class);
            startActivity(i);
        }
        if(view.getId()==R.id.intentdata){
            i=new Intent(this, intnentdata.class);
            startActivity(i);
        }

    }
}
