package com.example.fyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TimetableAddMenu extends AppCompatActivity{
    private ActionBar toolbar;
    public static String REFRESH = "com.example.fyp.REFRESH";
    Main main;


    private EditText etTitle;
    private EditText etTime;
    private boolean saved = false;
    String mon, tue, wed, thu, fri;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_timetable_add);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Add Timetable");
        REFRESH = "1";

        final List<CheckBox> cbMon = new ArrayList<>();
        final List<CheckBox> cbTue = new ArrayList<>();
        final List<CheckBox> cbWed = new ArrayList<>();
        final List<CheckBox> cbThu = new ArrayList<>();
        final List<CheckBox> cbFri = new ArrayList<>();
        mon = tue = wed = thu= fri = "";
        etTitle = (EditText)TimetableAddMenu.this.findViewById(R.id.courseTitle);

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

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
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

                course = new Course();
                course.setTitle(etTitle.getText().toString());
                course.setMon(mon);
                course.setTue(tue);
                course.setWed(wed);
                course.setThu(thu);
                course.setFri(fri);

                CourseDbQueries dbq = new CourseDbQueries(new DbHelper(getApplicationContext()));
                if(dbq.insert(course) != 0) {
                    saved = true;
                    Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
                }
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
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
