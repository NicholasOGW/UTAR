package com.example.fyp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class ExpenditureEditMenu extends AppCompatActivity {
    public static final String EXTRA_EXPENDITURE = "com.example.fyp.EXPENDITURE";
    ArrayAdapter<CharSequence> adapter;
    private ActionBar actionBar;
    private Expenditure expenditure;
    private Spinner spinner;
    Integer selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_expenditure_edit);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Expenses");
    }

    public void onResume() {
        super.onResume();

        Intent intent = getIntent();
        long id = intent.getLongExtra(ExpenditureFragment.EXTRA_ID, 0);

        ExpenditureDbQueries dbq = new ExpenditureDbQueries(new DbHelper(getApplicationContext()));

        final String[] columns = {
                ExpenditureContract.ExpenditureEntry._ID,
                ExpenditureContract.ExpenditureEntry.COLUMN_TYPE,
                ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT,
                ExpenditureContract.ExpenditureEntry.COLUMN_TITLE,
                ExpenditureContract.ExpenditureEntry.COLUMN_DATE,
        };

        String selection = ExpenditureContract.ExpenditureEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        Cursor cursor = dbq.query(columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToNext()) {
            expenditure = new Expenditure(
                    cursor.getLong(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_TYPE)),
                    cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT)),
                    cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry.COLUMN_DATE))
            );

            final Spinner spinner = (Spinner)findViewById(R.id.spinnerPopupMenu);
            final EditText etTitle = (EditText) findViewById(R.id.expensesTitle);
            final EditText etAmount = (EditText) findViewById(R.id.amount);
            final String etDate = expenditure.getDate();
            adapter = ArrayAdapter.createFromResource(this, R.array.spinner_expenses_type, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(Integer.parseInt(expenditure.getType()));
            etTitle.setText(expenditure.getTitle());
            etAmount.setText(expenditure.getAmount());

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
                    expenditure.setType(String.valueOf(selectedItem));
                    expenditure.setAmount(etAmount.getText().toString());
                    expenditure.setTitle(etTitle.getText().toString());
                    expenditure.setDate(etDate);

                    ExpenditureDbQueries dbq = new ExpenditureDbQueries(new DbHelper(getApplicationContext()));
                    dbq.update(expenditure);

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
                    ExpenditureDbQueries dbq = new ExpenditureDbQueries(new DbHelper(getApplicationContext()));
                    dbq.delete(expenditure.getId());
                    finish();
                }
            });
        } else {
            Log.e("id not found", Long.toString(cursor.getLong(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry._ID))));
            finish();
        }
    }
}
