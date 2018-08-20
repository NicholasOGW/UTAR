package com.example.fyp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;


public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "task.db";
    public final static String ITABLE_NAME = "IMAGE_TABLE";
    private static final String TAG = "";
    ContentResolver mContentResolver;

    SQLiteDatabase db;

    private static final String TASK_TABLE =
            "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " (" +
                    TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY," +
                    TaskContract.TaskEntry.COLUMN_TYPE + " TEXT," +
                    TaskContract.TaskEntry.COLUMN_TITLE + " TEXT," +
                    TaskContract.TaskEntry.COLUMN_DATE + " TEXT," +
                    TaskContract.TaskEntry.COLUMN_TIME + " TEXT)";

    private static final String COURSE_TABLE =
            "CREATE TABLE " + CourseContract.CourseEntry.TABLE_NAME + " (" +
                    CourseContract.CourseEntry._ID + " INTEGER PRIMARY KEY," +
                    CourseContract.CourseEntry.COLUMN_TITLE + " TEXT," +
                    CourseContract.CourseEntry.COLUMN_MON + " TEXT," +
                    CourseContract.CourseEntry.COLUMN_TUES + " TEXT," +
                    CourseContract.CourseEntry.COLUMN_WED + " TEXT," +
                    CourseContract.CourseEntry.COLUMN_THUR + " TEXT," +
                    CourseContract.CourseEntry.COLUMN_FRI + " TEXT)";

    private static final String SUBJECT_TABLE =
            "CREATE TABLE " + SubjectContract.SubjectEntry.TABLE_NAME + " (" +
                    SubjectContract.SubjectEntry._ID + " INTEGER PRIMARY KEY," +
                    SubjectContract.SubjectEntry.COLUMN_TITLE + " TEXT)";

    public final static String COLUMN_TYPE = "type";
    public final static String COLUMN_IMAGE = "image";

    private final String IMAGE_TABLE =
            "CREATE TABLE " + ITABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_TYPE + " TEXT," +
                    COLUMN_IMAGE + " BLOB)";

    private static final String EP_TABLE =
            "CREATE TABLE " + ExpenditureContract.ExpenditureEntry.TABLE_NAME + " (" +
                    ExpenditureContract.ExpenditureEntry._ID + " INTEGER PRIMARY KEY," +
                    ExpenditureContract.ExpenditureEntry.COLUMN_TYPE + " TEXT," +
                    ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT + " TEXT," +
                    ExpenditureContract.ExpenditureEntry.COLUMN_TITLE + " TEXT," +
                    ExpenditureContract.ExpenditureEntry.COLUMN_DATE + " TEXT)";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mContentResolver = context.getContentResolver();

        db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE);
        db.execSQL(COURSE_TABLE);
        db.execSQL(SUBJECT_TABLE);
        db.execSQL(IMAGE_TABLE);
        db.execSQL(EP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITABLE_NAME);
        onCreate(db);
    }

    public void addToDb(String type, byte[] image){
        ContentValues cv = new  ContentValues();
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_IMAGE, image);
        try {
            db.insertOrThrow(ITABLE_NAME, null, cv);
            Log.i(TAG, "SAVED");

        } catch (SQLException e) {
            Log.i(TAG, "ERROR: " + e.toString());
        }
        db.close();
    }
}
