package com.smartdroidesign.timetasker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Basic database class for the application.
 * Only class using this must be AppProvider (this is why is not public)
 */
class AppDatabase extends SQLiteOpenHelper {
    private static final String TAG = "AppDatabase";

    public static final String DATABASE_NAME = "AppDatabase";
    public static final int DATABASE_VERSION = 1;

    // Implement the database as a singleton
    private static AppDatabase instance = null;

    /*
    This constructor is calling the super class one, passing context, database name and version.
    Third argument is null, it's used to provide a custom cursor, if you want to. If not, the default one will be used.
    Marked as private, as we only want a single instance of this class to exist.
    Should be better to implement a Singleton for this.
     */
    private AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "AppDatabase: constructor");
    }

    /**
     * Get an instance of the app's singleton database helper object
     *
     * @param contex the content providers context.
     * @return a SQLite database helper object.
     */
    static AppDatabase getInstance(Context contex) {
        if (instance == null) {
            Log.d(TAG, "getInstance: creating new instance");
            instance =  new AppDatabase(contex);
        }
        return instance;
    }

    /**
     * CREATE TABLE Tasks (_id INTEGER PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Description TEXT, SortOrder INTEGER, CategoryID INTEGER);
     * @param db
     * db.execSQL does all the work here: grabs the SQL instructions and executes them
     * onCreate is called automatically of the SQL Helper class detects the database must be created
     *Called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: starts");
        String sSQL; // Use a string variable to facilitate logging
//        sSQL = "CREATE TABLE Tasks (_id INTEGER PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Description TEXT, SortOrder INTEGER, CategoryID INTEGER);";
        sSQL = "CREATE TABLE " + TaskContract.TABLE_NAME + " ("
                + TaskContract.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
                + TaskContract.Columns.TASKS_NAME + " TEXT NOT NULL, "
                + TaskContract.Columns.TASKS_DESCRIPTION + " TEXT, "
                + TaskContract.Columns.TASKS_SORT_ORDER + " INTEGER);";
        Log.d(TAG, sSQL);
        db.execSQL(sSQL);

        Log.d(TAG, "onCreate: ends");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
