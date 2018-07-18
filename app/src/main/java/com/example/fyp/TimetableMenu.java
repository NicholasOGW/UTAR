package com.example.fyp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class TimetableMenu extends AppCompatActivity{
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_timetable);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Edit Timetable");
    }
}
