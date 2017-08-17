package com.example.raza.vocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
public class MainActivity extends AppCompatActivity {

    Button button_first, button_second;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = new RelativeLayout(this);
        textView = (TextView)findViewById(R.id.text1);
        button_first = (Button)findViewById(R.id.button);
        button_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               view();
            }
        });

        button_second = (Button)findViewById(R.id.button2);
        button_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    public void add()
    {
        Intent i = new Intent(this,Main2Activity.class);
        startActivity(i);
    }
    public void view()
    {
        Intent i = new Intent(this,Main3Activity.class);
        startActivity(i);
    }

}
