package com.example.libraryapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "books.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKS =
            " CREATE TABLE IF NOT EXISTS books ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            " titulo TEXT NOT NULL, " +
            " autor TEXT NOT NULL, " +
            " ano TEXT NOT NULL, " +
            " capa TEXT NOT NULL " +
            " ); ";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // não vamos tratar alterações
    }
}
