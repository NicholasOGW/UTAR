package com.example.fyp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseDbQueries {
    private DbHelper helper;

    public CourseDbQueries(DbHelper helper) {
        this.helper = helper;
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(
                CourseContract.CourseEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );
    }

    public long insert(Course course) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CourseContract.CourseEntry.COLUMN_TITLE, course.getTitle());
        values.put(CourseContract.CourseEntry.COLUMN_MON, course.getMon());
        values.put(CourseContract.CourseEntry.COLUMN_TUES, course.getTues());
        values.put(CourseContract.CourseEntry.COLUMN_WED, course.getWed());
        values.put(CourseContract.CourseEntry.COLUMN_THUR, course.getThur());
        values.put(CourseContract.CourseEntry.COLUMN_FRI, course.getFri());
//        values.put(CourseContract.CourseEntry.COLUMN_LOCATION, course.getlocation());

        long id = db.insert(CourseContract.CourseEntry.TABLE_NAME, null, values);
        course.setId(id);
        return id;
    }

    public int update(Course course) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CourseContract.CourseEntry.COLUMN_TITLE, course.getTitle());
        values.put(CourseContract.CourseEntry.COLUMN_MON, course.getMon());
        values.put(CourseContract.CourseEntry.COLUMN_TUES, course.getTues());
        values.put(CourseContract.CourseEntry.COLUMN_WED, course.getWed());
        values.put(CourseContract.CourseEntry.COLUMN_THUR, course.getThur());
        values.put(CourseContract.CourseEntry.COLUMN_FRI, course.getFri());
//        values.put(CourseContract.CourseEntry.COLUMN_LOCATION, course.getLocation());

        String selection = CourseContract.CourseEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(course.getId())};

        return db.update(
                CourseContract.CourseEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    public void delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = CourseContract.CourseEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        db.delete(CourseContract.CourseEntry.TABLE_NAME, selection, selectionArgs);
    }
}
