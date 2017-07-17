package com.example.bhavyesharma.kaamkarle;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.sergiocasero.revealfab.RevealFAB;

import java.util.ArrayList;
import java.util.Collections;

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
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mReminderList=new ArrayList<>();
        mRecyclerView.setAdapter(mAdapter);
        updateReminderList();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mReminderList,from,to);
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mReminderList.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }



    private void updateReminderList() {
        ReminderOpenHelper reminderOpenHelper = ReminderOpenHelper.getReminderOpenHelperInstance(this);
        mReminderList.clear();
        SQLiteDatabase database = reminderOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(reminderOpenHelper.REMINDER_TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(ReminderOpenHelper.REMINDER_TITLE));
            String details = cursor.getString(cursor.getColumnIndex(ReminderOpenHelper.REMINDER_DETAILS));
            String time = cursor.getString(cursor.getColumnIndex(ReminderOpenHelper.REMINDER_TIME));
            String date = cursor.getString(cursor.getColumnIndex(ReminderOpenHelper.REMINDER_DATE));
            String id = cursor.getString(cursor.getColumnIndex(ReminderOpenHelper.REMINDER_ID));
            Reminder R = new Reminder(title, details, date, time);
            mReminderList.add(R);

        }
        mAdapter.notifyDataSetChanged();
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
