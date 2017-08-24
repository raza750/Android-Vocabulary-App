package com.example.raza.vocabulary;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    public Button add, meaning;
    public EditText editText_word, editText_sen;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText_word = (EditText)findViewById(R.id.editText3);
        editText_sen = (EditText)findViewById(R.id.editText4);
        meaning = (Button)findViewById(R.id.button6);
        add = (Button)findViewById(R.id.button5);
        db = new Database(this,null,null,1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTodb();
            }
        });
        meaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMeaning();
            }
        });
    }
    public void addTodb()
    {
         String s = editText_word.getText().toString();
         String s1 = editText_sen.getText().toString();
        if( s.equals("") || s1.equals(""))
        {
            Toast.makeText(this,"Add word and sentence",Toast.LENGTH_SHORT).show();
        }else{
         Words w = new Words(s,s1);
         db.add(w);
         editText_sen.getText().clear();
         editText_word.getText().clear();
        }
    }

    public void searchMeaning()
    {
        boolean x = networkcheck();
        if(x == false)
        {
            Toast.makeText(this,"Internet not available",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String s = editText_word.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String query = "http://www.dictionary.com/browse/"+s;
            intent.setData(Uri.parse(query));
            startActivity(intent);

        }catch (Exception e){System.out.print(e);};
    }

    private boolean networkcheck(){

        ConnectivityManager cm  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }
}
