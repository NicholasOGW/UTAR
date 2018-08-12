package com.example.fyp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;

public class ExpenditureAddInitialMenu extends AppCompatActivity{
    private ActionBar toolbar;
    private EditText etIAmount;
    private boolean saved = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_expenditure_add_initial);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Add Initial Amount");

        etIAmount = ExpenditureAddInitialMenu.this.findViewById(R.id.initial_amount);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Double iAmount = Double.parseDouble(etIAmount.getText().toString());

                SharedPreferences settings = getSharedPreferences("INITIAL_AMOUNT", MODE_PRIVATE);
                // Writing data to SharedPreferences
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("key", iAmount.toString());
                editor.commit();

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
