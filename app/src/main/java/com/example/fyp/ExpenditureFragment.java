package com.example.fyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class ExpenditureFragment extends Fragment {

    private ListView listView;
    private TextView tvInitial;
    private TextView tvRemaining;
    public static final String EXTRA_ID = "com.example.fyp.ID";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_expenditure, container, false);

        tvInitial = v.findViewById(R.id.initial_amount);
        tvRemaining = v.findViewById(R.id.final_amount);

        tvInitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenditureFragment.this.getActivity(), ExpenditureAddInitialMenu.class);
                ExpenditureFragment.this.startActivity(intent);
            }
        });

        listView = v.findViewById(R.id.list_expenditure);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor)adapterView.getItemAtPosition(position);

                Intent intent = new Intent(ExpenditureFragment.this.getActivity(), ExpenditureEditMenu.class);
                intent.putExtra(EXTRA_ID, cursor.getLong(cursor.getColumnIndex(ExpenditureContract.ExpenditureEntry._ID)));
                ExpenditureFragment.this.startActivity(intent);
            }
        });

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ExpenditureAddMenu.class);
                startActivity(in);
            }
        });

        tvRemaining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenditureFragment.this.getActivity(), ExpenditurePieChart.class);
                ExpenditureFragment.this.startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        ExpenditureDbQueries dbq = new ExpenditureDbQueries(new DbHelper(getActivity().getApplicationContext()));

        String[] columns = {
                ExpenditureContract.ExpenditureEntry._ID,
                ExpenditureContract.ExpenditureEntry.COLUMN_TYPE,
                ExpenditureContract.ExpenditureEntry.COLUMN_AMOUNT,
                ExpenditureContract.ExpenditureEntry.COLUMN_TITLE,
                ExpenditureContract.ExpenditureEntry.COLUMN_DATE,
        };
        Cursor cursor = dbq.query(columns, null, null, null, null, ExpenditureContract.ExpenditureEntry.COLUMN_DATE + " ASC");

        ExpenditureCursorAdapter adapter = new ExpenditureCursorAdapter(getActivity(), cursor, 0);

        listView.setAdapter(adapter);

        SharedPreferences settings = this.getActivity().getSharedPreferences("INITIAL_AMOUNT", MODE_PRIVATE);
        String value = settings.getString("key", "");

        if (value.equals("")) {
            value = "0";
        }

        tvInitial.setText(value);
        Log.d(TAG, value);
        double expensesTotal = dbq.calculateTotal();
        double initialAmount = Double.parseDouble(value);
        double ramainingAmount = initialAmount - expensesTotal;
        tvRemaining.setText(Double.toString(ramainingAmount));
    }
}
