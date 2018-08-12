package com.example.fyp;

import android.provider.BaseColumns;

public class ExpenditureContract {

    private ExpenditureContract() {}

    public static class ExpenditureEntry implements BaseColumns {
        public static final String TABLE_NAME = "expenditure";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
    }
}
