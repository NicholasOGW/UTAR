package com.example.fyp;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * The configuration screen for the {@link WidgetCountdown WidgetCountdown} AppWidget.
 */
public class WidgetCountdownConfigureActivity extends Activity {

    public static final String EXTRA_ID = "com.example.fyp.ID";
    private static final String PREFS_NAME = "com.example.fyp.WidgetCountdown";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    private  ListView listView;
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    EditText mAppWidgetText1;
    EditText mAppWidgetText2;
    EditText mAppWidgetText3;
    Task task;

    public void mOnClickListener(){

            final Context context = WidgetCountdownConfigureActivity.this;
        Log.d("", "testing mOnClickListener");
            // When the button is clicked, store the string locally


        Intent intent1 = getIntent();
        long id1 = intent1.getLongExtra(WidgetCountdownConfigureActivity.EXTRA_ID, 0);
        Log.d("", "testing list clicked 2");
        TaskDbQueries dbq = new TaskDbQueries(new DbHelper(getApplicationContext()));

        final String[] columns = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COLUMN_TYPE,
                TaskContract.TaskEntry.COLUMN_TITLE,
                TaskContract.TaskEntry.COLUMN_DATE,
                TaskContract.TaskEntry.COLUMN_TIME,

        };
        Log.d("", "testing list clicked 3");

        String selection = TaskContract.TaskEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString
                (id1)};

        Cursor cursor1 = dbq.query(columns, selection, selectionArgs, null, null, null);
        Log.d("", "testing list clicked 4");

        if (cursor1.moveToNext()) {
            task = new Task(
                    cursor1.getLong(cursor1.getColumnIndex(TaskContract.TaskEntry._ID)),
                    cursor1.getString(cursor1.getColumnIndex(TaskContract.TaskEntry.COLUMN_TYPE)),
                    cursor1.getString(cursor1.getColumnIndex(TaskContract.TaskEntry.COLUMN_TITLE)),
                    cursor1.getString(cursor1.getColumnIndex(TaskContract.TaskEntry.COLUMN_DATE)),
                    cursor1.getString(cursor1.getColumnIndex(TaskContract.TaskEntry.COLUMN_TIME))
            );
        }
        Log.d("", "testing list clicked 5");

        mAppWidgetText1 = findViewById(R.id.appwidget_text1);
        mAppWidgetText2 = findViewById(R.id.appwidget_text2);
        mAppWidgetText3 = findViewById(R.id.appwidget_text3);
        Log.d("", "testing list clicked 6");

//            String widgetText1 = cursor1.getString(cursor1.getColumnIndex(TaskContract.TaskEntry.COLUMN_TITLE));
//            String widgetText2 = cursor1.getString(cursor1.getColumnIndex(TaskContract.TaskEntry.COLUMN_TIME));
//            String widgetText3 = cursor1.getString(cursor1.getColumnIndex(TaskContract.TaskEntry.COLUMN_DATE));
        String widgetText1 = "AAA";
        String widgetText2 = "BBB";
        String widgetText3 = "CCC";
            saveTitlePref(context, mAppWidgetId, widgetText1);
            saveTitlePref(context, mAppWidgetId, widgetText2);
            saveTitlePref(context, mAppWidgetId, widgetText3);

            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            WidgetCountdown.updateAppWidget(context, appWidgetManager, mAppWidgetId);

            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
    }

    public WidgetCountdownConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
        if (titleValue != null) {
            return titleValue;
        } else {
            return "COUNTDOWN";
        }
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.widget_countdown_configure);

        listView = findViewById(R.id.list_all);

        TaskDbQueries dbq = new TaskDbQueries(new DbHelper(WidgetCountdownConfigureActivity.this.getApplicationContext()));

        String[] columns = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COLUMN_TITLE,
                TaskContract.TaskEntry.COLUMN_DATE,
                TaskContract.TaskEntry.COLUMN_TIME
        };
        Cursor cursor = dbq.query(columns, null, null, null, null, TaskContract.TaskEntry.COLUMN_DATE + " ASC");

        TaskCursorAdapter adapter = new TaskCursorAdapter(WidgetCountdownConfigureActivity.this, cursor, 0);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                Log.d("", "testing list clicked");
                Intent intent = new Intent();
                intent.putExtra(EXTRA_ID, cursor.getLong(cursor.getColumnIndex(TaskContract.TaskEntry._ID)));

                mOnClickListener();

                Intent intent2 = getIntent();
                Bundle extras = intent2.getExtras();
                if (extras != null) {
                    mAppWidgetId = extras.getInt(
                            AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
                    Log.d("", "testing list clicked 7");

                }

                // If this activity was started with an intent without an app widget ID, finish with an error.
                if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                    Log.d("", "testing list clicked 8");

                    finish();
                    return;
                }
                Log.d("", "testing list clicked 9");

                mAppWidgetText1.setText(loadTitlePref(WidgetCountdownConfigureActivity.this, mAppWidgetId));
//                mAppWidgetText2.setText(loadTitlePref(WidgetCountdownConfigureActivity.this, mAppWidgetId));
//                mAppWidgetText3.setText(loadTitlePref(WidgetCountdownConfigureActivity.this, mAppWidgetId));
            }
        });
    }
}

