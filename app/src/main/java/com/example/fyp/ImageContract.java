package com.example.fyp;

import android.provider.BaseColumns;

public class ImageContract {

    private ImageContract() {}

    public static class ImageEntry implements BaseColumns {
        public static final String TABLE_NAME = "course";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_IMAGE = "image";
    }
}
