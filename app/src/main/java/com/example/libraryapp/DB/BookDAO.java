package com.example.libraryapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DAO<Book> {

    public BookDAO(Context context){
        super(context);
    }

    @Override
    public void save(Book entity) {
        SQLiteDatabase database = openToRead();

        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", entity.getTitulo());
        contentValues.put("autor", entity.getAutor());
        contentValues.put("ano", entity.getAno());
        contentValues.put("capa", entity.getCapa());

        database.insert("books", null, contentValues);

        database.close();
    }

    @Override
    public void delete(Book entity) {
        SQLiteDatabase database = openToWrite();

        String[] params = {entity.getTitulo()};
        database.delete("books", " titulo = ? ", params);

        database.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase database = openToWrite();

        database.execSQL("delete from books");

        database.close();
    }

    @Override
    public List<Book> listAll() {
        SQLiteDatabase database = openToRead();
        List<Book> books = new ArrayList<>();

        String sql = " SELECT * FROM books ";

        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String titulo = cursor.getString(
                    cursor.getColumnIndexOrThrow("titulo"));
            String autor = cursor.getString(
                    cursor.getColumnIndexOrThrow("autor"));
            String ano = cursor.getString(
                    cursor.getColumnIndexOrThrow("ano"));
            String capa = cursor.getString(
                    cursor.getColumnIndexOrThrow("capa"));

            Book book = new Book(id, titulo, autor, ano, capa);
            books.add(book);
        }

        cursor.close();
        database.close();

        return books;
    }
}
