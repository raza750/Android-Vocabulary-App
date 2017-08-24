package com.example.raza.vocabulary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String database = "words.db";
    private static final String table = "vocab";
    public static final String coloum0 = "id";
    public static final String coloum1 = "words";
    public static final String coloum2 = "sentences";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + table + "("+ coloum0+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                      coloum1 + " TEXT ," + coloum2 + " TEXT " + " )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF EXISTS" + table);
        onCreate(db);
    }

    public void add(Words w)
    {
        ContentValues values = new ContentValues();
        values.put(coloum1,w.getWord());
        values.put(coloum2,w.getSentence());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long row = sqLiteDatabase.insert(table,null,values);
        Log.v("row inserted" ,String.valueOf(row));
    }

    public ArrayList<Words> view()
    {
        ArrayList<Words> al = new ArrayList();
        String sql = "SELECT * FROM " +table;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c= database.rawQuery(sql,null);
        c.moveToFirst();

        while( !c.isAfterLast())
        {
            try{
                int id = c.getInt(c.getColumnIndex(coloum0));
                String word = c.getString(c.getColumnIndex(coloum1));
                String sentence = c.getString(c.getColumnIndex(coloum2));
                Words w = new Words(id,word,sentence);
                al.add(w);
                c.moveToNext();
            }catch (Exception e){System.out.println(e);}
        }
        return al;
    }

    public void delete(int id)
    {
        String sql = "DELETE FROM "+ table + " WHERE "+ coloum0 + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void edit(int i, String s2)
    {
        String sql = "UPDATE "+table+" SET "+ coloum2 +" = '" + s2 + "'" +" WHERE "+ coloum0 +" = " + i;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }
}
