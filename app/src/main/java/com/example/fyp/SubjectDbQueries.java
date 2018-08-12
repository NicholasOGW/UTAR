package com.example.fyp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SubjectDbQueries {
    private DbHelper helper;

    public SubjectDbQueries(DbHelper helper) {
        this.helper = helper;
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(
                SubjectContract.SubjectEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );
    }

    public long insert(Subject subject) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SubjectContract.SubjectEntry.COLUMN_TITLE, subject.getTitle());

        long id = db.insert(SubjectContract.SubjectEntry.TABLE_NAME, null, values);
        subject.setId(id);
        return id;
    }

    public int update(Subject subject) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SubjectContract.SubjectEntry.COLUMN_TITLE, subject.getTitle());

        String selection = SubjectContract.SubjectEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(subject.getId())};

        return db.update(
                SubjectContract.SubjectEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    public void delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = SubjectContract.SubjectEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        db.delete(SubjectContract.SubjectEntry.TABLE_NAME, selection, selectionArgs);
    }
}
