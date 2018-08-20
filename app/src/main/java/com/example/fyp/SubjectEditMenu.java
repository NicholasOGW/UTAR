package com.example.fyp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class SubjectEditMenu extends AppCompatActivity {
    ArrayAdapter<CharSequence> adapter;
    private ActionBar actionBar;
    private Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_subject_edit);
    }

    public void onResume() {
        super.onResume();
        actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Subject");

        Intent intent = getIntent();
        long id = intent.getLongExtra(SubjectFragment.EXTRA_ID, 0);

       SubjectDbQueries dbq = new SubjectDbQueries(new DbHelper(getApplicationContext()));

        final String[] columns = {
                SubjectContract.SubjectEntry._ID,
                SubjectContract.SubjectEntry.COLUMN_TITLE,
        };

        String selection = SubjectContract.SubjectEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString
                (id)};

        Cursor cursor = dbq.query(columns, selection, selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            subject = new Subject(
                    cursor.getLong(cursor.getColumnIndex(SubjectContract.SubjectEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(SubjectContract.SubjectEntry.COLUMN_TITLE))
            );

            final EditText etTitle = findViewById(R.id.subjectTitle);
            etTitle.setText(subject.getTitle());

            Button btnUpdate = findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    subject.setTitle(etTitle.getText().toString());
                    SubjectDbQueries dbq = new SubjectDbQueries(new DbHelper(getApplicationContext()));
                    dbq.update(subject);
                    finish();
                }
            });

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
                    dbq.delete(subject.getId());
                    finish();
                }
            });
        }
        else {
            Log.e("id not found", Long.toString(cursor.getLong(cursor.getColumnIndex(SubjectContract.SubjectEntry._ID))));
            finish();
        }
    }
}
