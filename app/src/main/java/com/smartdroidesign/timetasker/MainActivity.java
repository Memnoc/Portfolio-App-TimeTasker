package com.smartdroidesign.timetasker;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/*
* DATABASE SCHEMA
*
* create table tasks (_id integer primary key not null, Name text not null, Description text not null, SortOrder integer);
CREATE TABLE tasks (_id integer primary key not null, Name text not null, Description text not null, SortOrder integer);
insert into tasks (Name, Description) values('TaskTimer', 'TaskTimer app creation');
insert into tasks (Name, Description, sortorder) values('Android N', 'Android N course', 2);
insert into tasks (Name, Description, sortorder) values('Java', 'Java  course', 0);

SELECT Tasks;
1|TaskTimer|TaskTimer app creation|
2|Android N|Android N course|2
3|Java|Java  course|0
*
*
* */


/**
 * LIFE OF THE QUERY
 *
 * 1) A call to getContentResolver().query(Uri, String, String, String, String) is made.
 * The call invokes the Content Resolver’s query method, not the ContentProvider’s.
 *
 * 2) When the query method is invoked, the Content Resolver parses the uri argument and extracts its authority.
 *
 * 3) The Content Resolver directs the request to the content provider registered with the (unique) authority.
 * This is done by calling the Content Provider’s query method.
 *
 * 4) When the Content Provider’s query method is invoked,
 * the query is performed and a Cursor is returned (or an exception is thrown).
 * The resulting behavior depends entirely on the Content Provider’s implementation.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] projection = { TaskContract.Columns.TASKS_NAME, TaskContract.Columns.TASKS_DESCRIPTION};
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(TaskContract.CONTENT_URI,
//        Cursor cursor = contentResolver.query(TaskContract.buildTaskUri(3),
                projection,
                null,
                null,
                TaskContract.Columns.TASKS_SORTORDER);

        if(cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while(cursor.moveToNext()) {
                for(int i=0; i<cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }


//        AppDatabase appDatabase = AppDatabase.getInstance(this);
//        final SQLiteDatabase db = appDatabase.getReadableDatabase();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
