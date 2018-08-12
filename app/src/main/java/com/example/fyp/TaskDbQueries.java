package com.example.fyp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskDbQueries {
    private DbHelper helper;

    public TaskDbQueries(DbHelper helper) {
        this.helper = helper;
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );
    }

    public long insert(Task task) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_TYPE, task.getType());
        values.put(TaskContract.TaskEntry.COLUMN_TITLE, task.getTitle());
        values.put(TaskContract.TaskEntry.COLUMN_DATE, task.getDate());
        values.put(TaskContract.TaskEntry.COLUMN_TIME, task.getTime());

        long id = db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
        task.setId(id);
        return id;
    }

    public int update(Task task) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_TYPE, task.getType());
        values.put(TaskContract.TaskEntry.COLUMN_TITLE, task.getTitle());
        values.put(TaskContract.TaskEntry.COLUMN_DATE, task.getDate());
        values.put(TaskContract.TaskEntry.COLUMN_TIME, task.getTime());

        String selection = TaskContract.TaskEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(task.getId())};

        return db.update(
                TaskContract.TaskEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    public void delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = TaskContract.TaskEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        db.delete(TaskContract.TaskEntry.TABLE_NAME, selection, selectionArgs);
    }
}
