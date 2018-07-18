package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ExpenditureMenu2 extends AppCompatActivity{
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_expenditure_setting);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Expenditure Planning Setting");
    }
}