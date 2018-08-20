package com.example.fyp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.fyp.DbHelper.COLUMN_IMAGE;

public class ImageCursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public ImageCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imageView = view.findViewById(R.id.item_image);
        byte [] encodeByte= cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        imageView.setImageBitmap(bitmap);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item_image, parent, false);
    }
}
