package com.example.fyp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TimetableFragment extends Fragment{

    DbHelper dbHelper;
    private Course course;
    CourseTableLayout courseTable;
    StudentCourse studentCourse;
    ArrayList<CourseInfo> courseInfoList;
    Intent intent;
    Cursor cursor;
    public static String EXTRA_NAME = "com.example.fyp.COURSE_NAME";
    public static String REFRESH = "com.example.fyp.REFRESH";


//    boolean allowRefresh;

//    public void setAllowRefresh(boolean allowRefresh){
//        this.allowRefresh = allowRefresh;
//    }


//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_timetable, container, false);

        courseTable = v.findViewById(R.id.courseTable);
        studentCourse = new StudentCourse();
        courseInfoList = new ArrayList<>();

//        FragmentTransaction tr = getFragmentManager().beginTransaction();
//        tr.replace(R.id.frame_container, yourFragmentInstance);
//        tr.commit()
//        courseTable.setOnCourseClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CourseInfo item = (CourseInfo) v.getTag();
//                Toast.makeText(getActivity(), item +"Clicked", Toast.LENGTH_SHORT);
//            }
//        });
        courseTable.setOnCourseClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseInfo item = (CourseInfo) view.getTag();
                Log.d(item.getName(), item.getName()+ "=NAME");
                Intent in = new Intent(getActivity(), TimetableEditMenu.class);
                EXTRA_NAME = item.getName();
                startActivity(in);
//                Log.d("onClick", "onClick is called!");
            }
        });


        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), TimetableAddMenu.class);
                startActivity(in);
            }
        });

//            allowRefresh = false;
            return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        CourseDbQueries dbq = new CourseDbQueries(new DbHelper(getActivity().getApplicationContext()));

        final String[] columns = {
                CourseContract.CourseEntry._ID,
                CourseContract.CourseEntry.COLUMN_TITLE,
                CourseContract.CourseEntry.COLUMN_MON,
                CourseContract.CourseEntry.COLUMN_TUES,
                CourseContract.CourseEntry.COLUMN_WED,
                CourseContract.CourseEntry.COLUMN_THUR,
                CourseContract.CourseEntry.COLUMN_FRI,
        };

        Cursor cursor = dbq.query(columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            course = new Course(
                    cursor.getLong(cursor.getColumnIndex(CourseContract.CourseEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_MON)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_TUES)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_WED)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_THUR)),
                    cursor.getString(cursor.getColumnIndex(CourseContract.CourseEntry.COLUMN_FRI))
            );

            CourseInfo courseInfo = new CourseInfo();
            courseInfo.setName(course.getTitle());
            courseInfo.setCourseTime(course.getMon(), course.getTues(), course.getWed(), course.getThur(), course.getFri(), "", "");
            courseInfoList.add(courseInfo);
        }

        studentCourse.setCourseList(courseInfoList);
        courseTable.setStudentCourse(studentCourse);
    }
}

