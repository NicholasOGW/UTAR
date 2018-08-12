package com.example.fyp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ImageDbQueries {
    private DbHelper helper;

    public ImageDbQueries(DbHelper helper) {
        this.helper = helper;
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(
                ImageContract.ImageEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );
    }

    public long insert(Image image) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ImageContract.ImageEntry.COLUMN_TYPE, image.getType());
        values.put(ImageContract.ImageEntry.COLUMN_IMAGE, image.getImage());

        long id = db.insert(ImageContract.ImageEntry.TABLE_NAME, null, values);
        image.setId(id);
        return id;
    }

    public int update(Image image) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ImageContract.ImageEntry.COLUMN_TYPE, image.getType());
        values.put(ImageContract.ImageEntry.COLUMN_IMAGE, image.getImage());

        String selection = ImageContract.ImageEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(image.getId())};

        return db.update(
                ImageContract.ImageEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    public void delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = ImageContract.ImageEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        db.delete(ImageContract.ImageEntry.TABLE_NAME, selection, selectionArgs);
    }
}
