package com.example.fyp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CourseCursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public CourseCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = (TextView)view.findViewById(R.id.courseTitle);
//        TextView tvMon = (TextView)view.findViewById(R.id.timeSelector);
//        TextView tvTues = (TextView)view.findViewById(R.id.timeSelector);
//        TextView tvWed = (TextView)view.findViewById(R.id.timeSelector);
//        TextView tvThur = (TextView)view.findViewById(R.id.timeSelector);
//        TextView tvFri = (TextView)view.findViewById(R.id.timeSelector);
//        TextView tvLocation = (TextView)view.findViewById(R.id.);
        String title = cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_TITLE));
        String mon = cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_MON));
        String tues = cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_TUES));
        String wed = cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_WED));
        String thur = cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_THUR));
        String fri = cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_FRI));
//        String date = cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_LOCATION));
        tvTitle.setText(title);
//        tvMon.setText(mon);
//        tvTues.setText(tues);
//        tvWed.setText(wed);
//        tvThur.setText(thur);
//        tvFri.setText(fri);
//        tvLocation.setText(location);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_timetable, parent, false);
    }
}
