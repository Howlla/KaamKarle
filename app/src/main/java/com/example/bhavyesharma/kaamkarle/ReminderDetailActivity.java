package com.example.bhavyesharma.kaamkarle;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class ReminderDetailActivity extends AppCompatActivity {
    int id;
    EditText reminderTitleTextView, reminderDetailTextView, reminderDateEditText, reminderTimeEditText;
    long date;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail);
        reminderTitleTextView=(EditText)findViewById(R.id.reminderTitleTextView);
        reminderDetailTextView=(EditText)findViewById(R.id.reminderDetailTextView);
        reminderDateEditText=(EditText)findViewById(R.id.reminderDateEditText);
        reminderTimeEditText=(EditText)findViewById(R.id.reminderTimeEditText);
        Button submitButton=(Button)findViewById(R.id.reminderSubmitButton);
        Intent i = getIntent();
        id=i.getIntExtra(IntentConstants.REMINDER_ID, -1);
        if(id!=-1){
            String title = i.getStringExtra(IntentConstants.REMINDER_TITLE);
            reminderTitleTextView.setText(title);
            String details = i.getStringExtra(IntentConstants.REMINDER_DETAILS);
            reminderDetailTextView.setText(IntentConstants.REMINDER_DETAILS);
        }
submitButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String newTitle=reminderTitleTextView.getText().toString();
        String newDetail=reminderDetailTextView.getText().toString();
        String newDate=reminderDateEditText.getText().toString();
        String newTime=reminderTimeEditText.getText().toString();
        setReminder(newTime,newDate,newTitle);

        ReminderOpenHelper reminderOpenHelper=ReminderOpenHelper.getReminderOpenHelperInstance(ReminderDetailActivity.this);
        SQLiteDatabase database = reminderOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ReminderOpenHelper.REMINDER_TITLE,newTitle);
        cv.put(ReminderOpenHelper.REMINDER_DETAILS,newDetail);
        cv.put(ReminderOpenHelper.REMINDER_DATE,newDate);
        cv.put(ReminderOpenHelper.REMINDER_TIME,newTime);
        database.insert(ReminderOpenHelper.REMINDER_TABLE_NAME,null,cv);
        setResult(RESULT_OK);
        finish();
    }
});
        reminderDateEditText = (EditText) findViewById(R.id.reminderDateEditText);
        reminderDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                int month = newCalendar.get(Calendar.MONTH);  // Current month
                int year = newCalendar.get(Calendar.YEAR);   // Current year
                showDatePicker(ReminderDetailActivity.this, year, month, 1);
            }
        });

    }
    public void showDatePicker(Context context, int initialYear, int initialMonth, int initialDay) {

        // Creating datePicker dialog object
        // It requires context and listener that is used when a date is selected by the user.

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    //This method is called when the user has finished selecting a date.
                    // Arguments passed are selected year, month and day
                    @Override
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {

                        // To get epoch, You can store this date(in epoch) in database
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        date = calendar.getTime().getTime();
                        // Setting date selected in the edit text
                        reminderDateEditText.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, initialYear, initialMonth, initialDay);

        //Call show() to simply show the dialog
        datePickerDialog.show();

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
    public void setReminder(String newTime, String newDate, String newTitle) {
        String[] time=newTime.split(":");
        String[] date=newDate.split("/");
        PendingIntent pendingIntent;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,(Integer.parseInt(date[1])-1));
        calendar.set(Calendar.YEAR, Integer.parseInt(date[2]));
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(date[0]));

        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        calendar.set(Calendar.SECOND, 0);
        //calendar.set(Calendar.AM_PM,Calendar.PM);

        Intent myIntent = new Intent(ReminderDetailActivity.this, MyReceiver.class);
        myIntent.putExtra("title",newTitle);
        pendingIntent = PendingIntent.getBroadcast(ReminderDetailActivity.this, 0, myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

}
