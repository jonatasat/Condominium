package com.example.android.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.myapplication.classes.DBCreate;
import com.example.android.myapplication.pojo.Visit;

/**
 * Created by Jonatas on 03/07/2017.
 */

public class DBController {

    private SQLiteDatabase db;
    private DBCreate bd;

    public DBController(Context context){
        bd = new DBCreate(context);
    }

    public String insertVisit(Visit visit){
        ContentValues values;
        long result = 0;

        db = bd.getWritableDatabase();
        values = new ContentValues();
        values.put(DBCreate.NAME, visit.getName());
        values.put(DBCreate.PHONE, visit.getPhone());
        values.put(DBCreate.DOCUMENT, visit.getDocument());
        values.put(DBCreate.CARPLATE, visit.getCarPlate());

        result = db.insert(DBCreate.TABLE, null, values);
        db.close();

        if(result == -1){
            return "Erro ao inserir registro";

        }else{
            return "Registro inserido com sucesso";
        }
    }

    public Cursor listVisits(){
        Cursor cursor;
        String[] fields =  {bd.ID,bd.NAME};
        db = bd.getReadableDatabase();
        cursor = db.query(bd.TABLE, fields, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor listVisitById(int id){
        Cursor cursor;
        String[] fields = {bd.ID,bd.NAME,bd.PHONE,bd.DOCUMENT,bd.CARPLATE};
        String where = DBCreate.ID + "=" +id;
        db = bd.getReadableDatabase();
        cursor = db.query(bd.TABLE,fields,where,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String updateVisitById(Visit visit){
        ContentValues values;
        String where;
        long result = 0;

        db = bd.getWritableDatabase();

        where = DBCreate.ID + "=" + visit.getId();

        values = new ContentValues();
        values.put(DBCreate.NAME, visit.getName());
        values.put(DBCreate.PHONE, visit.getPhone());
        values.put(DBCreate.DOCUMENT, visit.getDocument());
        values.put(DBCreate.CARPLATE, visit.getCarPlate());

        result = db.update(DBCreate.TABLE, values, where, null);
        db.close();

        if(result == -1){
            return "Erro ao alterar registro";

        }else{
            return "Registro alterado com sucesso";
        }

    }

    public void deleteVisit(Visit visit){
        String where = DBCreate.ID + "=" + visit.getId();
        db = bd.getReadableDatabase();
        db.delete(DBCreate.TABLE,where,null);
        db.close();
    }
}
