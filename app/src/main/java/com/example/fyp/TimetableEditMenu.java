package com.example.fyp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class TimetableEditMenu extends AppCompatActivity{

    private ActionBar toolbar;
    private EditText etTitle;
    private EditText etTime;
    private boolean saved = false;
    private String mon = "";
    private String tues = "";
    private String wed = "";
    private String thur = "";
    private String fri = "";
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_timetable_edit);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Edit Timetable");

        String title = TimetableFragment.EXTRA_NAME;

        etTitle = (EditText)TimetableEditMenu.this.findViewById(R.id.courseTitle);
        etTitle.setText(title);

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

        String selection = CourseContract.CourseEntry.COLUMN_TITLE + " = ?";
        String[] selectionArgs = {(title)};

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

            final CheckBox cbmon1, cbmon2, cbmon3, cbmon4, cbmon5, cbmon6, cbmon7, cbmon8, cbmon9, cbmon10, cbmon11, cbmon12, cbmon0;
            final CheckBox cbtue1, cbtue2, cbtue3, cbtue4, cbtue5, cbtue6, cbtue7, cbtue8, cbtue9, cbtue10, cbtue11, cbtue12, cbtue0;
            final CheckBox cbwed1, cbwed2, cbwed3, cbwed4, cbwed5, cbwed6, cbwed7, cbwed8, cbwed9, cbwed10, cbwed11, cbwed12, cbwed0;
            final CheckBox cbthu1, cbthu2, cbthu3, cbthu4, cbthu5, cbthu6, cbthu7, cbthu8, cbthu9, cbthu10, cbthu11, cbthu12, cbthu0;
            final CheckBox cbfri1, cbfri2, cbfri3, cbfri4, cbfri5, cbfri6, cbfri7, cbfri8, cbfri9, cbfri10, cbfri11, cbfri12, cbfri0;

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
            cbmon0 = findViewById(R.id.mon0);

            cbtue1 = findViewById(R.id.tues1);
            cbtue2 = findViewById(R.id.tues2);
            cbtue3 = findViewById(R.id.tues3);
            cbtue4 = findViewById(R.id.tues4);
            cbtue5 = findViewById(R.id.tues5);
            cbtue6 = findViewById(R.id.tues6);
            cbtue7 = findViewById(R.id.tues7);
            cbtue8 = findViewById(R.id.tues8);
            cbtue9 = findViewById(R.id.tues9);
            cbtue10 = findViewById(R.id.tues10);
            cbtue11 = findViewById(R.id.tues11);
            cbtue12 = findViewById(R.id.tues12);
            cbtue0 = findViewById(R.id.tues0);

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
            cbwed0 = findViewById(R.id.wed0);

            cbthu1 = findViewById(R.id.thur1);
            cbthu2 = findViewById(R.id.thur2);
            cbthu3 = findViewById(R.id.thur3);
            cbthu4 = findViewById(R.id.thur4);
            cbthu5 = findViewById(R.id.thur5);
            cbthu6 = findViewById(R.id.thur6);
            cbthu7 = findViewById(R.id.thur7);
            cbthu8 = findViewById(R.id.thur8);
            cbthu9 = findViewById(R.id.thur9);
            cbthu10 = findViewById(R.id.thur10);
            cbthu11 = findViewById(R.id.thur11);
            cbthu12 = findViewById(R.id.thur12);
            cbthu0 = findViewById(R.id.thur0);

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
            cbfri0 = findViewById(R.id.fri0);


            Button btnUpdate = findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (cbmon1.isChecked())
                        mon += "6 ";
                    if (cbmon2.isChecked())
                        mon += "7 ";
                    if (cbmon3.isChecked())
                        mon += "8 ";
                    if (cbmon4.isChecked())
                        mon += "9 ";
                    if (cbmon5.isChecked())
                        mon += "10 ";
                    if (cbmon6.isChecked())
                        mon += "11 ";
                    if (cbmon7.isChecked())
                        mon += "12 ";
                    if (cbmon8.isChecked())
                        mon += "1 ";
                    if (cbmon9.isChecked())
                        mon += "2 ";
                    if (cbmon10.isChecked())
                        mon += "3 ";
                    if (cbmon11.isChecked())
                        mon += "4 ";
                    if (cbmon12.isChecked())
                        mon += "5 ";
                    if (cbmon0.isChecked())
                        mon += "13 ";

                    if (cbtue8.isChecked())
                        tues += "1 ";
                    if (cbtue9.isChecked())
                        tues += "2 ";
                    if (cbtue10.isChecked())
                        tues += "3 ";
                    if (cbtue11.isChecked())
                        tues += "4 ";
                    if (cbtue12.isChecked())
                        tues += "5 ";
                    if (cbtue1.isChecked())
                        tues += "6 ";
                    if (cbtue2.isChecked())
                        tues += "7 ";
                    if (cbtue3.isChecked())
                        tues += "8 ";
                    if (cbtue4.isChecked())
                        tues += "9 ";
                    if (cbtue5.isChecked())
                        tues += "10 ";
                    if (cbtue6.isChecked())
                        tues += "11 ";
                    if (cbtue7.isChecked())
                        tues += "12 ";
                    if (cbtue0.isChecked())
                        tues += "13 ";

                    if (cbwed8.isChecked())
                        wed += "1 ";
                    if (cbwed9.isChecked())
                        wed += "2 ";
                    if (cbwed10.isChecked())
                        wed += "3 ";
                    if (cbwed11.isChecked())
                        wed += "4 ";
                    if (cbwed12.isChecked())
                        wed += "5 ";
                    if (cbwed1.isChecked())
                        wed += "6 ";
                    if (cbwed2.isChecked())
                        wed += "7 ";
                    if (cbwed3.isChecked())
                        wed += "8 ";
                    if (cbwed4.isChecked())
                        wed += "9 ";
                    if (cbwed5.isChecked())
                        wed += "10 ";
                    if (cbwed6.isChecked())
                        wed += "11 ";
                    if (cbwed7.isChecked())
                        wed += "12 ";
                    if (cbwed0.isChecked())
                        wed += "13 ";

                    if (cbthu8.isChecked())
                        thur += "1 ";
                    if (cbthu9.isChecked())
                        thur += "2 ";
                    if (cbthu10.isChecked())
                        thur += "3 ";
                    if (cbthu11.isChecked())
                        thur += "4 ";
                    if (cbthu12.isChecked())
                        thur += "5 ";
                    if (cbthu1.isChecked())
                        thur += "6 ";
                    if (cbthu2.isChecked())
                        thur += "7 ";
                    if (cbthu3.isChecked())
                        thur += "8 ";
                    if (cbthu4.isChecked())
                        thur += "9 ";
                    if (cbthu5.isChecked())
                        thur += "10 ";
                    if (cbthu6.isChecked())
                        thur += "11 ";
                    if (cbthu7.isChecked())
                        thur += "12 ";
                    if (cbthu8.isChecked())
                        thur += "13 ";

                    if (cbfri8.isChecked())
                        fri += "1 ";
                    if (cbfri9.isChecked())
                        fri += "2 ";
                    if (cbfri10.isChecked())
                        fri += "3 ";
                    if (cbfri11.isChecked())
                        fri += "4 ";
                    if (cbfri12.isChecked())
                        fri += "5 ";
                    if (cbfri1.isChecked())
                        fri += "6 ";
                    if (cbfri2.isChecked())
                        fri += "7 ";
                    if (cbfri3.isChecked())
                        fri += "8 ";
                    if (cbfri4.isChecked())
                        fri += "9 ";
                    if (cbfri5.isChecked())
                        fri += "10 ";
                    if (cbfri6.isChecked())
                        fri += "11 ";
                    if (cbfri7.isChecked())
                        fri += "12 ";
                    if (cbfri0.isChecked())
                        fri += "13 ";

                    String title = etTitle.getText().toString();
                    String mond = mon;
                    String tuesd = tues;
                    String wedd = wed;
                    String thurd = thur;
                    String frid = fri;

                    course.setTitle(etTitle.getText().toString());
                    course.setMon(mond);
                    course.setTues(tuesd);
                    course.setWed(wedd);
                    course.setThur(thurd);
                    course.setFri(frid);

                    CourseDbQueries dbq = new CourseDbQueries(new DbHelper(getApplicationContext()));
                    dbq.update(course);
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
                    finish();
                }
            });

        }
    }
}
