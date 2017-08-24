package com.example.raza.vocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    TextView tv;
    ListView list;
    Database db;
    Words w;
    ArrayList al = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv = (TextView)findViewById(R.id.textView);
        list = (ListView)findViewById(R.id.list);
        db = new Database(this,null,null,1);
        words();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, al);
        list.setAdapter(adapter);
        list.setOnItemClickListener(item);
    }

    private AdapterView.OnItemClickListener item = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          try{
              int bid = db.view().get(position).getId();
              String sen = db.view().get(position).getSentence();
              String word1 = db.view().get(position).getWord();

              Intent intent = new Intent(Main3Activity.this,Main4Activity.class);
              intent.putExtra("sentence",sen);
              intent.putExtra("word",word1);
              intent.putExtra("id",bid);
              startActivity(intent);
        }catch (Exception e){System.out.println(e);}}
    };

    public void words()
    {
        for(int i = 0; i < db.view().size(); i++)
        {
            String wrd = db.view().get(i).getWord();
            al.add(wrd);
        }
    }
}
