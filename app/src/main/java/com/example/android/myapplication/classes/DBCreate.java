package com.example.android.myapplication.classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonatas on 03/07/2017.
 */

public class DBCreate extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";
    public static final String TABLE = "visits";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String DOCUMENT = "document";
    public static final String CARPLATE = "carplate";

    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE "+TABLE+"("
                + ID + " integer primary key autoincrement, "
                + NAME + " text, "
                + PHONE + " text, "
                + DOCUMENT + " text, "
                + CARPLATE + " text "
                +")";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE);
        onCreate(db);
    }

    public DBCreate(Context context){
        super(context, "database.db", null, 1);
    }
}
