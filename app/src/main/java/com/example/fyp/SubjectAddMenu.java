package com.example.fyp;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SubjectAddMenu extends AppCompatActivity{
    private ActionBar actionBar;

    private EditText etTitle;
    private boolean saved = false;

    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_subject_add);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Subject");

        etTitle = (EditText)SubjectAddMenu.this.findViewById(R.id.subjectTitle);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();

                SubjectDbQueries dbq = new SubjectDbQueries(new DbHelper(getApplicationContext()));
                Subject subject = new Subject(title);
                if(dbq.insert(subject) != 0) {
                    saved = true;
                }
                finish();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
