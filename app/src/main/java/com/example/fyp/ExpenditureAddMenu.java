package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExpenditureAddMenu extends AppCompatActivity{
    private ActionBar toolbar;
    private EditText etAmount;
    private EditText etTitle;
    Spinner spinner;
    Integer selectedItem;
    ArrayAdapter<CharSequence> adapter;
    private boolean saved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_expenditure_add);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Add Expenses");

        //get current date and time
        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        spinner = findViewById(R.id.spinnerPopupMenu);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_expenses_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_SHORT).show();
                selectedItem = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etAmount = ExpenditureAddMenu.this.findViewById(R.id.amount);
        etTitle = ExpenditureAddMenu.this.findViewById(R.id.expensesTitle);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = selectedItem.toString();
                String amount = etAmount.getText().toString();
                String title = etTitle.getText().toString();


                ExpenditureDbQueries dbq = new ExpenditureDbQueries(new DbHelper(getApplicationContext()));
                Expenditure expenditure = new Expenditure(type, amount, title, currentDateTimeString);
                if(dbq.insert(expenditure) != 0) {
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
