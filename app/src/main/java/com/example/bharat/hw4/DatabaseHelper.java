package com.example.bharat.hw4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bharat on 3/21/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "matches.db";
    private static final String TABLE_NAME = "matches";
    private static final String COLUMN_SYNONYM = "synonym";
    private static final String COLUMN_ANTONYM = "antonym";
    private static final String TABLE_CREATE = "create table matches (synonym text primary key not null , " + "antonym text not null );";
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+DATABASE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
    public String searchWords(String word){
        db= this.getReadableDatabase();
        String query = "select * from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "Word Not Found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                b = cursor.getString(1);
                if(word.equals(a))
                    return b;
                if(word.equals(b))
                    return a;
                if(!word.equals(a)||!word.equals(b)){
                    b = "Word Not Found";
                }
            }
            while (cursor.moveToNext());

        }
        return b;

    }
    public void insertMatch(Match m){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ANTONYM, m.getAntonym());
        values.put(COLUMN_SYNONYM, m.getSynonym());
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
}
