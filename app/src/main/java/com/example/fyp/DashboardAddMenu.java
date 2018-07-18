package com.example.fyp;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

public class DashboardAddMenu extends AppCompatActivity{
    private ActionBar actionBar;

    private EditText etTitle;
    private EditText etDate;
    private EditText etTime;
    private boolean saved = false;

    Spinner spinner;
    Integer selectedItem;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_dashboard_add);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Event");

        spinner = findViewById(R.id.spinnerPopupMenu);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_option, android.R.layout.simple_spinner_item);
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

        etTitle = (EditText)DashboardAddMenu.this.findViewById(R.id.eventTitle);
        etDate = (EditText)DashboardAddMenu.this.findViewById(R.id.date);
        etTime = (EditText)DashboardAddMenu.this.findViewById(R.id.time);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = selectedItem.toString();
                String title = etTitle.getText().toString();
                String date = etDate.getText().toString();
                String time = etTime.getText().toString();

                TaskDbQueries dbq = new TaskDbQueries(new TaskDbHelper(getApplicationContext()));
                Task task = new Task(type, title, date,time);
                if(dbq.insert(task) != 0) {
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
    public void showDatePickerDialog(View view) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View view) {
        DialogFragment fragment = new TimePickerFragment();
        fragment.show(getSupportFragmentManager(), "timePicker");
    }
}
