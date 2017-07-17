package com.example.bhavyesharma.kaamkarle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bhavyesharma on 15/07/17.
 */

public class ReminderOpenHelper extends SQLiteOpenHelper {
    public final static String REMINDER_TABLE_NAME="Reminders";
    public final static String REMINDER_TITLE="title";
    public final static String REMINDER_DETAILS="details";
    public final static String REMINDER_ID="id";
    public final static String REMINDER_TIME="time";
    public final static String REMINDER_DATE="date";
    public static ReminderOpenHelper reminderOpenHelper;
    public static ReminderOpenHelper getReminderOpenHelperInstance(Context context){
        if (reminderOpenHelper==null){
            reminderOpenHelper =new ReminderOpenHelper(context);
        }
        return reminderOpenHelper;
    }

    private ReminderOpenHelper(Context context) {

        super(context, "Reminders.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
String query="create table "+REMINDER_TABLE_NAME+"("+REMINDER_ID+
        " integer primary key autoincrement, " + REMINDER_TITLE +" text, "
                + REMINDER_TIME + " text, "
                + REMINDER_DATE + " text,"+REMINDER_DETAILS+"text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
