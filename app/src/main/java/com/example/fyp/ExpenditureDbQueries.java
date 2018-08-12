package com.example.fyp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ExpenditureDbQueries {
    private DbHelper helper;

    public ExpenditureDbQueries(DbHelper helper) {
        this.helper = helper;
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(
                ExpenditureContract.ExpenditureEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );
    }

    public long insert(Expenditure expenditure) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExpenditureContract.ExpenditureEntry.COLUMN_TYPE, expenditure.getType());
        values.put(ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT, expenditure.getAmount());
        values.put(ExpenditureContract.ExpenditureEntry.COLUMN_TITLE, expenditure.getTitle());
        values.put(ExpenditureContract.ExpenditureEntry.COLUMN_DATE, expenditure.getDate());

        long id = db.insert(ExpenditureContract.ExpenditureEntry.TABLE_NAME, null, values);
        expenditure.setId(id);
        return id;
    }

    public int update(Expenditure expenditure) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExpenditureContract.ExpenditureEntry.COLUMN_TYPE, expenditure.getType());
        values.put(ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT, expenditure.getAmount());
        values.put(ExpenditureContract.ExpenditureEntry.COLUMN_TITLE, expenditure.getTitle());
        values.put(ExpenditureContract.ExpenditureEntry.COLUMN_DATE, expenditure.getDate());

        String selection = ExpenditureContract.ExpenditureEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(expenditure.getId())};

        return db.update(
                ExpenditureContract.ExpenditureEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    public void delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = ExpenditureContract.ExpenditureEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        db.delete(ExpenditureContract.ExpenditureEntry.TABLE_NAME, selection, selectionArgs);
    }

    public double calculateTotal() {
        SQLiteDatabase db = helper.getReadableDatabase();
        double total = 0;

        Cursor cursor = db.rawQuery("SELECT SUM(" + ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT + ") as Total FROM " + ExpenditureContract.ExpenditureEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
        }
        Log.d(Double.toString(total), "sum");
        return total;
    }
}
