package com.example.fyp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TimetableEditMenu extends AppCompatActivity{

    private ActionBar toolbar;
    private EditText etTitle;
    private EditText etTime;
    private boolean saved = false;
    String mon, tue, wed, thu, fri;
    Long id;
    private Course course;
    Gson gson = new Gson();

    final List<CheckBox> cbMon = new ArrayList<>();
    final List<CheckBox> cbTue = new ArrayList<>();
    final List<CheckBox> cbWed = new ArrayList<>();
    final List<CheckBox> cbThu = new ArrayList<>();
    final List<CheckBox> cbFri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_timetable_edit);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Edit Timetable");

        CourseInfo item = (CourseInfo)getIntent().getSerializableExtra("courseInfo");

        mon = tue= wed = thu= fri = "";
        id = item.getId();
        etTitle = (EditText)TimetableEditMenu.this.findViewById(R.id.courseTitle);
        etTitle.setText(item.getName());

        CourseDbQueries dbq = new CourseDbQueries(new DbHelper(getApplicationContext()));

        final String[] columns = {
                CourseContract.CourseEntry._ID,
                CourseContract.CourseEntry.COLUMN_TITLE,
                CourseContract.CourseEntry.COLUMN_MON,
                CourseContract.CourseEntry.COLUMN_TUES,
                CourseContract.CourseEntry.COLUMN_WED,
                CourseContract.CourseEntry.COLUMN_THUR,
                CourseContract.CourseEntry.COLUMN_FRI,
        };

        String selection = CourseContract.CourseEntry._ID + " = ?";
        String[] selectionArgs = {(Long.toString(id))};

        Cursor cursor = dbq.query(columns, selection, selectionArgs, null, null, null);

        if(cursor.moveToNext()) {
            course = new Course(
                    cursor.getLong(cursor.getColumnIndex(CourseContract.CourseEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_MON)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_TUES)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_WED)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_THUR)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_FRI))
            );

            for(int i = 1; i <= 13; i++){
                String idMon = "mon" + i;
                String idTue = "tue" + i;
                String idWed = "wed" + i;
                String idThu = "thu" + i;
                String idFri = "fri" + i;
                int resMon = getResources().getIdentifier(idMon, "id", getPackageName());
                int resTue = getResources().getIdentifier(idTue, "id", getPackageName());
                int resWed = getResources().getIdentifier(idWed, "id", getPackageName());
                int resThu = getResources().getIdentifier(idThu, "id", getPackageName());
                int resFri = getResources().getIdentifier(idFri, "id", getPackageName());
                cbMon.add((CheckBox)findViewById(resMon));
                cbTue.add((CheckBox)findViewById(resTue));
                cbWed.add((CheckBox)findViewById(resWed));
                cbThu.add((CheckBox)findViewById(resThu));
                cbFri.add((CheckBox)findViewById(resFri));
            }

            String[] monList = course.getMon().split(" ");
            String[] tueList = course.getTue().split(" ");
            String[] wedList = course.getWed().split(" ");
            String[] thuList = course.getThu().split(" ");
            String[] friList = course.getFri().split(" ");

            for(String mon : monList){
                if(isInteger(mon))
                    cbMon.get(Integer.valueOf(mon)-1).setChecked(true);
            }
            for(String tue : tueList){
                if(isInteger(tue))
                    cbTue.get(Integer.valueOf(tue)-1).setChecked(true);
            }
            for(String wed : wedList){
                if(isInteger(wed))
                    cbWed.get(Integer.valueOf(wed)-1).setChecked(true);
            }
            for(String thu : thuList){
                if(isInteger(thu))
                    cbThu.get(Integer.valueOf(thu)-1).setChecked(true);
            }
            for(String fri : friList){
                if(isInteger(fri))
                    cbFri.get(Integer.valueOf(fri)-1).setChecked(true);
            }

            Button btnUpdate = findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for(int i = 1; i <= 13; i++){
                        if(cbMon.get(i-1).isChecked()){
                            mon = mon + i + " ";
                        }
                        if(cbTue.get(i-1).isChecked()){
                            tue = tue + i + " ";
                        }
                        if(cbWed.get(i-1).isChecked()){
                            wed = wed + i + " ";
                        }
                        if(cbThu.get(i-1).isChecked()){
                            thu = thu + i + " ";
                        }
                        if(cbFri.get(i-1).isChecked()){
                            fri = fri + i + " ";
                        }
                    }

                    course.setTitle(etTitle.getText().toString());
                    course.setMon(mon);
                    course.setTue(tue);
                    course.setWed(wed);
                    course.setThu(thu);
                    course.setFri(fri);

                    CourseDbQueries dbq = new CourseDbQueries(new DbHelper(getApplicationContext()));
                    dbq.update(course);
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            });

            Button btnCancel = findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            Button btnDelete = findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CourseDbQueries dbq = new CourseDbQueries(new DbHelper(getApplicationContext()));
                    dbq.delete(course.getId());
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            });

        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
