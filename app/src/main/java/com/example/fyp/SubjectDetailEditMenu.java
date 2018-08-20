package com.example.fyp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SubjectDetailEditMenu extends AppCompatActivity{
    private ActionBar actionBar;
    private Image image;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_subject_edit);
    }

    public void onResume() {
        super.onResume();
        actionBar = getSupportActionBar();
        actionBar.setTitle("Image Detail");

        Intent intent = getIntent();
        String id = SubjectDetailMenu.EXTRA_IMAGE;

        ImageDbQueries dbq = new ImageDbQueries(new DbHelper(getApplicationContext()));

//        final String[] columns = {
//                SubjectContract.SubjectEntry._ID,
//                SubjectContract.SubjectEntry.COLUMN_TITLE,
//        };

        dbHelper = new DbHelper(getApplicationContext());
        String selection = DbHelper.COLUMN_TYPE + " = ?";
        String[] selectionArgs = {id};

        Cursor cursor = dbq.query(null, selection, selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            image = new Image(
                    cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_TYPE)),
                    cursor.getBlob(cursor.getColumnIndex(DbHelper.COLUMN_IMAGE))
            );

            Button btnCancel = findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            Button btnDelete = findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SubjectDbQueries dbq = new SubjectDbQueries(new DbHelper(getApplicationContext()));
                    dbq.delete(image.getId());
                    finish();
                }
            });
        }
        else {
            finish();
        }
    }
}
