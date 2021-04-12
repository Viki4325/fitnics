package com.group12.fitnics.business;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.group12.fitnics.objects.Notification;

public class NotificationBuilder extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notify = (Notification) intent.getSerializableExtra("Notification");

        NotificationHelper notificationHelper = new NotificationHelper(context,notify);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(notify);
        notificationHelper.getManager().notify(0,nb.build());
    }
}
