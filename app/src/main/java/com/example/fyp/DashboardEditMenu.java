package com.example.fyp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DashboardEditMenu extends AppCompatActivity {
    public static final String EXTRA_TASK = "com.example.fyp.TASK";
    ArrayAdapter<CharSequence> adapter;
    private ActionBar actionBar;
    private Task task;
    Integer selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_dashboard_edit);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Event");
    }

    public void onResume() {
        super.onResume();

        Intent intent = getIntent();
        long id = intent.getLongExtra(AllTab.EXTRA_ID, 0);

       TaskDbQueries dbq = new TaskDbQueries(new TaskDbHelper(getApplicationContext()));

        final String[] columns = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COLUMN_TYPE,
                TaskContract.TaskEntry.COLUMN_TITLE,
                TaskContract.TaskEntry.COLUMN_DATE,
                TaskContract.TaskEntry.COLUMN_TIME
        };

        String selection = TaskContract.TaskEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        Cursor cursor = dbq.query(columns, selection, selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            task = new Task(
                    cursor.getLong(cursor.getColumnIndex(TaskContract.TaskEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TYPE)),
                    cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TIME))
            );

            setTitle(task.getTitle());

            final Spinner spinner = (Spinner)findViewById(R.id.spinnerPopupMenu);
            final EditText etTitle = (EditText)findViewById(R.id.eventTitle);
            final EditText etDate = (EditText)findViewById(R.id.date);
            final EditText etTime = (EditText)findViewById(R.id.time);

            adapter = ArrayAdapter.createFromResource(this, R.array.spinner_option, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(Integer.parseInt(task.getType()));
            etTitle.setText(task.getTitle());
            etDate.setText(task.getDate());
            etTime.setText(task.getTime());

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

            Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.setType(String.valueOf(selectedItem));
                    task.setTitle(etTitle.getText().toString());
                    task.setDate(etDate.getText().toString());
                    task.setTime(etTime.getText().toString());

                    TaskDbQueries dbq = new TaskDbQueries(new TaskDbHelper(getApplicationContext()));
                    dbq.update(task);

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

            Button btnDelete = (Button) findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 TaskDbQueries dbq = new TaskDbQueries(new TaskDbHelper(getApplicationContext()));
                 dbq.delete(task.getId());
                 finish();
                }
            });
        }
        else {
            Log.e("id not found", Long.toString(cursor.getLong(cursor.getColumnIndex(TaskContract.TaskEntry._ID))));
            finish();
        }
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
