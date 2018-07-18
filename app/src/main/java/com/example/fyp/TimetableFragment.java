package com.example.fyp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import com.example.fyp.CourseTableLayout;
import com.example.fyp.CourseInfo;
import com.example.fyp.StudentCourse;

public class TimetableFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timetable, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), com.example.fyp.TimetableMenu.class);
                startActivity(in);
            }
        });

        CourseTableLayout courseTable = (CourseTableLayout) v.findViewById(R.id.courseTable);
        StudentCourse studentCourse = new StudentCourse();
        ArrayList<CourseInfo> courseInfoList = new ArrayList<>();

        // Add course1 - sample1
        CourseInfo courseInfo1 = new CourseInfo();
        courseInfo1.setName("Course 1");
        courseInfo1.setCourseTime("8 9", "10 11", "12 1", "3 4", "", "", "");
        courseInfoList.add(courseInfo1);

        // Add course2 - sample2
        CourseInfo courseInfo2 = new CourseInfo();
        courseInfo2.setName("Course 2");
        courseInfo2.setCourseTime(new String[]{"4", "5", "3", "6 7 8", "", "", ""});
        courseInfoList.add(courseInfo2);

        // Set timetable
        studentCourse.setCourseList(courseInfoList);
        courseTable.setStudentCourse(studentCourse);

        return v;
    }
}

