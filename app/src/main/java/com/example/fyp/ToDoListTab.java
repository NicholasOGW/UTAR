package com.example.fyp;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoListTab extends Fragment {
    public static final String EXTRA_ID = "com.example.fyp.ID";
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.tab_todolist, container, false);
        listView = (ListView)v.findViewById(R.id.list_todolist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor)adapterView.getItemAtPosition(position);

                Intent intent = new Intent(ToDoListTab.this.getActivity(), DashboardEditMenu.class);
                intent.putExtra(EXTRA_ID, cursor.getLong(cursor.getColumnIndex(TaskContract.TaskEntry._ID)));
                ToDoListTab.this.startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        TaskDbQueries dbq = new TaskDbQueries(new TaskDbHelper(getActivity().getApplicationContext()));

        String[] columns = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COLUMN_TITLE,
                TaskContract.TaskEntry.COLUMN_DATE,
                TaskContract.TaskEntry.COLUMN_TIME
        };
        Cursor cursor = dbq.query(columns, "TYPE = 2", null, null, null, TaskContract.TaskEntry.COLUMN_DATE + " ASC");

        TaskCursorAdapter adapter = new TaskCursorAdapter(getActivity(), cursor, 0);

        listView.setAdapter(adapter);
    }
}