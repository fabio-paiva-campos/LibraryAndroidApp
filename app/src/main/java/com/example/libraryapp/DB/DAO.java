package com.example.libraryapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

abstract class DAO<T> {
    private DBHandler dbHandler = null;

    DAO(Context context){
        if (dbHandler == null){
            dbHandler = new DBHandler(context);
        }
    }

    SQLiteDatabase openToWrite(){
        return dbHandler.getWritableDatabase();
    }

    SQLiteDatabase openToRead(){
        return dbHandler.getReadableDatabase();
    }

    public abstract void save(T entity);
    public abstract void delete(T entity);
    public abstract void deleteAll();
    public abstract List<T> listAll();
}
