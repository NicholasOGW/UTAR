package com.example.fyp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ExpenditureCursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public ExpenditureCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvAmount = view.findViewById(R.id.item_amount);
        TextView tvTitle = view.findViewById(R.id.item_title);
        TextView tvDate = view.findViewById(R.id.item_date);
        String amount = cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT));
        String title = cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_TITLE));
        String date = cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_DATE));
        tvAmount.setText(amount);
        tvTitle.setText(title);
        tvDate.setText(date);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item_expenditure, parent, false);
    }
}
