package com.projetinho.projetolabmoveis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBManager {
    public static DBHelper dbHelper = null;

    public DBManager(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }
    }
    public void addLembretes(double latitude, double longitude){
        String sql = "INSERT INTO lembretes (latitude, longitude) VALUES("+latitude+","+longitude+")";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
    }

    public ArrayList<String> getLembretesStr(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT *FROM lembretes";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> lembretes= null;
        if(cursor != null && cursor.moveToFirst()){
            lembretes = new ArrayList<String>();
            do{
                lembretes.add(cursor.getString(1) +","+ cursor.getString(2));
            }while(cursor.moveToNext());
        }
        return lembretes;
    }

    public ArrayList<Lembrete> getLembretes(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT *FROM lembretes";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Lembrete> lembretes= null;
        if(cursor != null && cursor.moveToFirst()){
            lembretes = new ArrayList<Lembrete>();
            Lembrete aux;
            do{
                aux = new Lembrete("lalalal",(cursor.getString(1) +","+ cursor.getString(2)));
                lembretes.add(aux);
            }while(cursor.moveToNext());
        }
        return lembretes;
    }
}
