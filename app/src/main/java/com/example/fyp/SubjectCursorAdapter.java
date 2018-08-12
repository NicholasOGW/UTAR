package com.example.fyp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class SubjectCursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public SubjectCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = view.findViewById(R.id.item_title);

        String title = cursor.getString(cursor.getColumnIndex(SubjectContract.SubjectEntry.COLUMN_TITLE));

        tvTitle.setText(title);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item_subject, parent, false);
    }
}
