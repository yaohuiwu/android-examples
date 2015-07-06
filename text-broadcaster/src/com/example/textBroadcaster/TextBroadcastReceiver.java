package com.example.textBroadcaster;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Receive broadcast text, and show it using notification.
 */
public class TextBroadcastReceiver extends BroadcastReceiver{
    private static final String ACTIVITY_TAG = "TextBroadcaster";
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        showNotification(intent);
    }

    private void showNotification(Intent intent){

        String recString = intent.getExtras().getString("content");
        Log.i(ACTIVITY_TAG, "收到广播:" + recString);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, TextBroadcaster.class), 0);

        NotificationManager notManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification(
                R.drawable.ic_launcher,
                recString,
                System.currentTimeMillis());
        notification.setLatestEventInfo(context, recString, null, pendingIntent);

        notManager.notify(R.layout.main, notification);
    }
}
