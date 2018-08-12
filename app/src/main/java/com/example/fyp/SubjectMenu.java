package com.example.fyp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubjectMenu extends AppCompatActivity{
    private ActionBar toolbar;
    private EditText etTitle;
    private boolean saved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_subject_add);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Add Subject");

        etTitle = (EditText)SubjectMenu.this.findViewById(R.id.subjectTitle);

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
