package com.example.fyp;

import android.provider.BaseColumns;

public class CourseContract {

    private CourseContract() {}

    public static class CourseEntry implements BaseColumns {
        public static final String TABLE_NAME = "course";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_MON = "mon";
        public static final String COLUMN_TUES = "tues";
        public static final String COLUMN_WED = "wed";
        public static final String COLUMN_THUR = "thur";
        public static final String COLUMN_FRI = "fri";
//        public static final String COLUMN_LOCATION = "location";
    }
}
