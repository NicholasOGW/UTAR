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

public class TimetableAddMenu extends AppCompatActivity{
    private ActionBar toolbar;
    public static String REFRESH = "com.example.fyp.REFRESH";
    Main main;


    private EditText etTitle;
    private EditText etTime;
    private boolean saved = false;
    String mon, tue, wed, thu, fri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_timetable_add);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Add Timetable");
        REFRESH = "1";

        mon = tue= wed = thu= fri = "";
        etTitle = (EditText)TimetableAddMenu.this.findViewById(R.id.courseTitle);

        final CheckBox cbmon1, cbmon2, cbmon3, cbmon4, cbmon5, cbmon6, cbmon7, cbmon8, cbmon9, cbmon10, cbmon11, cbmon12, cbmon13;
        final CheckBox cbtue1, cbtue2, cbtue3, cbtue4, cbtue5, cbtue6, cbtue7, cbtue8, cbtue9, cbtue10, cbtue11, cbtue12, cbtue13;
        final CheckBox cbwed1, cbwed2, cbwed3, cbwed4, cbwed5, cbwed6, cbwed7, cbwed8, cbwed9, cbwed10, cbwed11, cbwed12, cbwed13;
        final CheckBox cbthu1, cbthu2, cbthu3, cbthu4, cbthu5, cbthu6, cbthu7, cbthu8, cbthu9, cbthu10, cbthu11, cbthu12, cbthu13;
        final CheckBox cbfri1, cbfri2, cbfri3, cbfri4, cbfri5, cbfri6, cbfri7, cbfri8, cbfri9, cbfri10, cbfri11, cbfri12, cbfri13;

        cbmon1 = findViewById(R.id.mon1);
        cbmon2 = findViewById(R.id.mon2);
        cbmon3 = findViewById(R.id.mon3);
        cbmon4 = findViewById(R.id.mon4);
        cbmon5 = findViewById(R.id.mon5);
        cbmon6 = findViewById(R.id.mon6);
        cbmon7 = findViewById(R.id.mon7);
        cbmon8 = findViewById(R.id.mon8);
        cbmon9 = findViewById(R.id.mon9);
        cbmon10 = findViewById(R.id.mon10);
        cbmon11 = findViewById(R.id.mon11);
        cbmon12 = findViewById(R.id.mon12);
        cbmon13 = findViewById(R.id.mon13);

        cbtue1 = findViewById(R.id.tue1);
        cbtue2 = findViewById(R.id.tue2);
        cbtue3 = findViewById(R.id.tue3);
        cbtue4 = findViewById(R.id.tue4);
        cbtue5 = findViewById(R.id.tue5);
        cbtue6 = findViewById(R.id.tue6);
        cbtue7 = findViewById(R.id.tue7);
        cbtue8 = findViewById(R.id.tue8);
        cbtue9 = findViewById(R.id.tue9);
        cbtue10 = findViewById(R.id.tue10);
        cbtue11 = findViewById(R.id.tue11);
        cbtue12 = findViewById(R.id.tue12);
        cbtue13 = findViewById(R.id.tue13);

        cbwed1 = findViewById(R.id.wed1);
        cbwed2 = findViewById(R.id.wed2);
        cbwed3 = findViewById(R.id.wed3);
        cbwed4 = findViewById(R.id.wed4);
        cbwed5 = findViewById(R.id.wed5);
        cbwed6 = findViewById(R.id.wed6);
        cbwed7 = findViewById(R.id.wed7);
        cbwed8 = findViewById(R.id.wed8);
        cbwed9 = findViewById(R.id.wed9);
        cbwed10 = findViewById(R.id.wed10);
        cbwed11 = findViewById(R.id.wed11);
        cbwed12 = findViewById(R.id.wed12);
        cbwed13 = findViewById(R.id.wed13);

        cbthu1 = findViewById(R.id.thu1);
        cbthu2 = findViewById(R.id.thu2);
        cbthu3 = findViewById(R.id.thu3);
        cbthu4 = findViewById(R.id.thu4);
        cbthu5 = findViewById(R.id.thu5);
        cbthu6 = findViewById(R.id.thu6);
        cbthu7 = findViewById(R.id.thu7);
        cbthu8 = findViewById(R.id.thu8);
        cbthu9 = findViewById(R.id.thu9);
        cbthu10 = findViewById(R.id.thu10);
        cbthu11 = findViewById(R.id.thu11);
        cbthu12 = findViewById(R.id.thu12);
        cbthu13 = findViewById(R.id.thu13);

        cbfri1 = findViewById(R.id.fri1);
        cbfri2 = findViewById(R.id.fri2);
        cbfri3 = findViewById(R.id.fri3);
        cbfri4 = findViewById(R.id.fri4);
        cbfri5 = findViewById(R.id.fri5);
        cbfri6 = findViewById(R.id.fri6);
        cbfri7 = findViewById(R.id.fri7);
        cbfri8 = findViewById(R.id.fri8);
        cbfri9 = findViewById(R.id.fri9);
        cbfri10 = findViewById(R.id.fri10);
        cbfri11 = findViewById(R.id.fri11);
        cbfri12 = findViewById(R.id.fri12);
        cbfri13 = findViewById(R.id.fri13);


        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbmon1.isChecked())
                    mon += "1 ";
                if (cbmon2.isChecked())
                    mon += "2 ";
                if (cbmon3.isChecked())
                    mon += "3 ";
                if (cbmon4.isChecked())
                    mon += "4 ";
                if (cbmon5.isChecked())
                    mon += "5 ";
                if (cbmon6.isChecked())
                    mon += "6 ";
                if (cbmon7.isChecked())
                    mon += "7 ";
                if (cbmon8.isChecked())
                    mon += "8 ";
                if (cbmon9.isChecked())
                    mon += "9 ";
                if (cbmon10.isChecked())
                    mon += "10 ";
                if (cbmon11.isChecked())
                    mon += "11 ";
                if (cbmon12.isChecked())
                    mon += "12 ";
                if (cbmon13.isChecked())
                    mon += "13 ";

                if (cbtue1.isChecked())
                    tue += "1 ";
                if (cbtue2.isChecked())
                    tue += "2 ";
                if (cbtue3.isChecked())
                    tue += "3 ";
                if (cbtue4.isChecked())
                    tue += "4 ";
                if (cbtue5.isChecked())
                    tue += "5 ";
                if (cbtue6.isChecked())
                    tue += "6 ";
                if (cbtue7.isChecked())
                    tue += "7 ";
                if (cbtue8.isChecked())
                    tue += "8 ";
                if (cbtue9.isChecked())
                    tue += "9 ";
                if (cbtue10.isChecked())
                    tue += "10 ";
                if (cbtue11.isChecked())
                    tue += "11 ";
                if (cbtue12.isChecked())
                    tue += "12 ";
                if (cbtue13.isChecked())
                    tue += "13 ";

                if (cbwed1.isChecked())
                    wed += "1 ";
                if (cbwed2.isChecked())
                    wed += "2 ";
                if (cbwed3.isChecked())
                    wed += "3 ";
                if (cbwed4.isChecked())
                    wed += "4 ";
                if (cbwed5.isChecked())
                    wed += "5 ";
                if (cbwed6.isChecked())
                    wed += "6 ";
                if (cbwed7.isChecked())
                    wed += "7 ";
                if (cbwed8.isChecked())
                    wed += "8 ";
                if (cbwed9.isChecked())
                    wed += "9 ";
                if (cbwed10.isChecked())
                    wed += "10 ";
                if (cbwed11.isChecked())
                    wed += "11 ";
                if (cbwed12.isChecked())
                    wed += "12 ";
                if (cbwed13.isChecked())
                    wed += "13 ";

                if (cbthu1.isChecked())
                    thu += "1 ";
                if (cbthu2.isChecked())
                    thu += "2 ";
                if (cbthu3.isChecked())
                    thu += "3 ";
                if (cbthu4.isChecked())
                    thu += "4 ";
                if (cbthu5.isChecked())
                    thu += "5 ";
                if (cbthu6.isChecked())
                    thu += "6 ";
                if (cbthu7.isChecked())
                    thu += "7 ";
                if (cbthu8.isChecked())
                    thu += "8 ";
                if (cbthu9.isChecked())
                    thu += "9 ";
                if (cbthu10.isChecked())
                    thu += "10 ";
                if (cbthu11.isChecked())
                    thu += "11 ";
                if (cbthu12.isChecked())
                    thu += "12 ";
                if (cbthu13.isChecked())
                    thu += "13 ";

                if (cbfri1.isChecked())
                    fri += "1 ";
                if (cbfri2.isChecked())
                    fri += "2 ";
                if (cbfri3.isChecked())
                    fri += "3 ";
                if (cbfri4.isChecked())
                    fri += "4 ";
                if (cbfri5.isChecked())
                    fri += "5 ";
                if (cbfri6.isChecked())
                    fri += "6 ";
                if (cbfri7.isChecked())
                    fri += "7 ";
                if (cbfri8.isChecked())
                    fri += "8 ";
                if (cbfri9.isChecked())
                    fri += "9 ";
                if (cbfri10.isChecked())
                    fri += "10 ";
                if (cbfri11.isChecked())
                    fri += "11 ";
                if (cbfri12.isChecked())
                    fri += "12 ";
                if (cbfri13.isChecked())
                    fri += "13 ";

                String title = etTitle.getText().toString();

                CourseDbQueries dbq = new CourseDbQueries(new DbHelper(getApplicationContext()));
                Course course = new Course(title, mon, tue, wed, thu, fri);
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
}
