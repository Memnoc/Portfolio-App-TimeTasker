package com.smartdroidesign.timetasker;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.smartdroidesign.timetasker.AppProvider.CONTENT_AUTHORITY;
import static com.smartdroidesign.timetasker.AppProvider.CONTENT_AUTHORITY_URI;

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

    /**
     * static field external classes can use to get the URI and access the Tasks table
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    /**
     * Appends the given ID to the end of the path
     * @param taskId
     * @return
     */
    static Uri buildTaskUri(long taskId) {
        return ContentUris.withAppendedId(CONTENT_URI, taskId);
    }

    static long getTaskId(Uri uri) {
        return ContentUris.parseId(uri);
    }
}
