package com.example.bhavyesharma.kaamkarle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.sergiocasero.revealfab.RevealFAB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Reminder> mReminderList;
    RecyclerView mRecyclerView;
    RecyclerAdapter mAdapter;
    RevealFAB revealFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        revealFAB = (RevealFAB) findViewById(R.id.reveal_fab);
        Intent intent = new Intent(MainActivity.this, ReminderDetailActivity.class);
        intent.putExtra(IntentConstants.REMINDER_ID,-1);
        revealFAB.setIntent(intent);
        revealFAB.setOnClickListener(new RevealFAB.OnClickListener() {
            @Override
            public void onClick(RevealFAB button, View v) {
                button.startActivityWithAnimation();

            }

        }
        );

    }
    @Override
    protected void onResume() {
        super.onResume();
        revealFAB.onResume();
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