package com.smartdroidesign.timetasker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
