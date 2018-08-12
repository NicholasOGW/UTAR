package com.example.fyp;

import android.provider.BaseColumns;

public class SubjectContract {

    private SubjectContract() {}

    public static class SubjectEntry implements BaseColumns {
        public static final String TABLE_NAME = "subject";
        public static final String COLUMN_TITLE = "title";
    }
}
