package com.projetinho.projetolabmoveis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Arthur on 14/07/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "Lembretes";
    private static String LEMBRETES_ITENS = "CREATE TABLE lembretes("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "latitude FLOAT,"+
            "longitude FLOAT"+
            ")";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LEMBRETES_ITENS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
