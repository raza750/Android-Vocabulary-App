package com.example.raza.vocabulary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import static android.view.View.GONE;

public class Main4Activity extends AppCompatActivity {

    String s,s1,s2;
    TextView tv_word, dis_sen;
    EditText edit_sen;
    Database db;
    Button edit, delete, save;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Bundle b = getIntent().getExtras();
        s = b.getString("word");
        s1 = b.getString("sentence");
        id = b.getInt("id");

        edit = (Button)findViewById(R.id.btn_edit);
        save = (Button)findViewById(R.id.save);
        delete = (Button)findViewById(R.id.btn_delete);
        tv_word = (TextView)findViewById(R.id.tv_word);
        dis_sen = (TextView)findViewById(R.id.tv_edit);
        edit_sen = (EditText) findViewById(R.id.editSentence);

        db = new Database(this,null,null,1);
        save.setVisibility(GONE);
        edit_sen.setVisibility(GONE);
        tv_word.setText(s);
        dis_sen.setText(s1);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletefromDb();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("in edit","edit block");
                save.setVisibility(View.VISIBLE);
                edit_sen.setVisibility(View.VISIBLE);
                delete.setVisibility(GONE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s2 = edit_sen.getText().toString();
                Log.e("s2 is",s2);
                if( s2.equals("")) {
                    Log.e("in save","save block when nothing is edited");
                    save.setVisibility(GONE);
                    edit_sen.setVisibility(GONE);
                    delete.setVisibility(View.VISIBLE);
                }else {
                    Log.e("in save","save block when text is edited");
                    dis_sen.setText(s2);
                    saveIndb();
                    delete.setVisibility(View.VISIBLE);
                    edit_sen.getText().clear();
                    edit_sen.setVisibility(GONE);
                }
            }
        });
    }

    public void saveIndb()
    {
          db.edit(id,s2);
          save.setVisibility(GONE);
    }
    public void deletefromDb()
    {
        db.delete(id);
        tv_word.setText("Deleted");
        edit_sen.setVisibility(GONE);
        dis_sen.setVisibility(GONE);
        edit.setVisibility(GONE);
        delete.setVisibility(GONE);
    }
}
