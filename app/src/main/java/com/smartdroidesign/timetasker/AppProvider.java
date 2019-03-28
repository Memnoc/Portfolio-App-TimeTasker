package com.smartdroidesign.timetasker;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * Provider for the TimeTasker app. This is the only class that knows about {@link AppDatabase}
 */
public class AppProvider extends ContentProvider {
    private static final String TAG = "AppProvider";

    // creating the helper
    private AppDatabase mOpenHelper;

    // field to store the URI matcher
    public static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final int TASKS = 100;
    private static final int TASKS_ID = 101;

    private static final int TIMINGS = 200;
    private static final int TIMINGS_ID = 201;

    /**
     * private static final int TASK_TIMINGS = 300;
     * private static final int TASK_TIMINGS_ID = 301;
     */

    private static final int TASKS_DURATION = 400;
    private static final int TASKS_DURATION_ID = 401;

    // Authority = name of the provider
    // URI = Uniform Resource Identifier
    static final String CONTENT_AUTHORITY = "com.smartdroidesign.timetasker.provider";
    // Public as it must be usable outside the class
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        // e.g. com.smartdroidesign.timetasker.provider/Tasks
        matcher.addURI(CONTENT_AUTHORITY, TaskContract.TABLE_NAME, TASKS);
        // e.g. com.smartdroidesign.timetasker.provider/Tasks/8
        matcher.addURI(CONTENT_AUTHORITY, TaskContract.TABLE_NAME + "/#", TASKS_ID);

//        matcher.addURI(CONTENT_AUTHORITY, TimingsContract.TABLE_NAME, TIMINGS);
//        matcher.addURI(CONTENT_AUTHORITY, TimingsContract.TABLE_NAME + "/#", TIMINGS_ID);
//
//        matcher.addURI(CONTENT_AUTHORITY, DurationsContract.TABLE_NAME, TASKS_DURATION);
//        matcher.addURI(CONTENT_AUTHORITY, DurationsContract.TABLE_NAME + "/#", TASKS_DURATION_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        // Getting an instance of the DB and storing it in mOpenHelper
        mOpenHelper = AppDatabase.getInstance(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query: called with URI" + uri);
        // Using a UriMatcher to work out what kind of Uri we have been given
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "query: match is" + match);
        // We can use the value of match to decide which Uri was passed into the method
        // This gives us the knowledge of knowing which table we should be using

        // Next we use a query builder to build the query to be executed against the DB
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // We build the equivalent of a WHERE query
        switch (match) {
            case TASKS:
                queryBuilder.setTables(TaskContract.TABLE_NAME);
                break;
            case TASKS_ID:
                queryBuilder.setTables(TaskContract.TABLE_NAME);
//                long taskId = TaskContract.getTaskId(uri);
//                queryBuilder.appendWhere(TaskContract.Columns._ID + "= " + taskId);
//                break;
//            case TIMINGS:
//                queryBuilder.setTables(TimingsContract.TABLE_NAME);
//                break;
//            case TIMINGS_ID:
//                queryBuilder.setTables(TimingsContract.TABLE_NAME);
//                long timingId = TimingsContract.getTimingId(uri);
//                queryBuilder.appendWhere(TimingsContract.Columns._ID + "= " + timingId);
//                break;
//            case TASKS_DURATION:
//                queryBuilder.setTables(DurationsContract.TABLE_NAME);
//                break;
//            case TASKS_DURATION_ID:
//                queryBuilder.setTables(DurationsContract.TABLE_NAME);
//                long durationId = DurationsContract.getDurationId(uri);
//                queryBuilder.appendWhere(DurationsContract.Columns._ID + "= " + durationId);
//                break;

                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        // Building the final query and running it against the DB
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
