package com.example.fyp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class TaskCursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public TaskCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = (TextView)view.findViewById(R.id.item_title);
        TextView tvDate = (TextView)view.findViewById(R.id.item_date);
        TextView tvTime = (TextView)view.findViewById(R.id.item_time);
        String title = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TITLE));
        String date = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DATE));
        String time = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TIME));
        tvTitle.setText(title);
        tvDate.setText(date);
        tvTime.setText(time);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item_dashboard, parent, false);
    }
}
