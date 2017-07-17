package com.example.bhavyesharma.kaamkarle;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by bhavyesharma on 18/07/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public static int i = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setAutoCancel(true).setSmallIcon(android.R.drawable.ic_menu_report_image).setContentTitle("JAAG JAO").setContentText("ALarm!!!");
        Intent resultIntent = new Intent(context,MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,i,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(i,builder.build());
    }
}