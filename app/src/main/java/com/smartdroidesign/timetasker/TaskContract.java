package com.smartdroidesign.timetasker;

import android.provider.BaseColumns;

public class TaskContract {

    static final String TABLE_NAME = "Tasks";

    // Inner classes to provide the constants for the DB
    // static lets me avoid instantiating the class to access the fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String TASKS_NAME = "Name";
        public static final String TASKS_DESCRIPTION = "Description";
        public static final String TASKS_SORTORDER = "SortOrder";

        private Columns() {
            // private constructor to prevent instantiation
        }
    }
}
