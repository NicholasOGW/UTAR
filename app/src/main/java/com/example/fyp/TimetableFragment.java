package com.example.fyp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import me.yaoandy107.ntut_timetable.CourseTableLayout;
import me.yaoandy107.ntut_timetable.model.CourseInfo;
import me.yaoandy107.ntut_timetable.model.StudentCourse;

public class TimetableFragment extends Fragment {
//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timetable, container, false);
        CourseTableLayout courseTable = (CourseTableLayout) v.findViewById(R.id.courseTable);
        StudentCourse studentCourse = new StudentCourse();
        ArrayList<CourseInfo> courseInfoList = new ArrayList<>();

        // Add course1 - sample1
        CourseInfo courseInfo1 = new CourseInfo();
        courseInfo1.setName("Course 1");
        courseInfo1.setCourseTime("1 2", "", "4", "", "", "", "");
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
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//
//    CourseTableLayout courseTable = findViewById(R.id.courseTable);
//    StudentCourse studentCourse = new StudentCourse();
//    ArrayList<CourseInfo> courseInfoList = new ArrayList<>();
//
//    // Add course1 - sample1
//    CourseInfo courseInfo1 = new CourseInfo();
//    courseInfo1.setName("Course 1");
//    courseInfo1.setCourseTime("1 2", "", "4", "", "", "", "");
//    courseInfoList.add(courseInfo1);
//
//    // Add course2 - sample2
//    CourseInfo courseInfo2 = new CourseInfo();
//    courseInfo2.setName("Course 2");
//    courseInfo2.setCourseTime(new String[]{"4", "5", "3", "6 7 8", "", "", ""});
//    courseInfoList.add(courseInfo2);
//
//    // Set timetable
//    studentCourse.setCourseList(courseInfoList);
//    courseTable.setStudentCourse(studentCourse);
}

