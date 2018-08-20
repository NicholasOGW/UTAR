package com.example.fyp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class SubjectFragment extends Fragment {

    private ListView listView;
    public static final String EXTRA_ID = "com.example.fyp.ID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_subject, container, false);
        listView = v.findViewById(R.id.list_subject);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor)adapterView.getItemAtPosition(position);
                Intent intent = new Intent(SubjectFragment.this.getActivity(), SubjectDetailMenu.class);
                intent.putExtra(EXTRA_ID, cursor.getLong(cursor.getColumnIndex(SubjectContract.SubjectEntry._ID)));
                SubjectFragment.this.startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor)adapterView.getItemAtPosition(position);
                Intent intent = new Intent(SubjectFragment.this.getActivity(), SubjectEditMenu.class);
                intent.putExtra(EXTRA_ID, cursor.getLong(cursor.getColumnIndex(SubjectContract.SubjectEntry._ID)));
                SubjectFragment.this.startActivity(intent);
                return true;
            }
        });

        FloatingActionButton fabAdd = (FloatingActionButton) v.findViewById(R.id.fab);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), com.example.fyp.SubjectAddMenu.class);
                startActivity(in);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        SubjectDbQueries dbq = new SubjectDbQueries(new DbHelper(getActivity().getApplicationContext()));
        String[] columns = {
                SubjectContract.SubjectEntry._ID,
                SubjectContract.SubjectEntry.COLUMN_TITLE
        };
        Cursor cursor = dbq.query(columns, null, null, null, null, SubjectContract.SubjectEntry.COLUMN_TITLE + " ASC");
        SubjectCursorAdapter adapter = new SubjectCursorAdapter(getActivity(), cursor, 0);
        listView.setAdapter(adapter);
    }
}
