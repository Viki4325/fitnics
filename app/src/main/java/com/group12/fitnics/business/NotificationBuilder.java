package com.group12.fitnics.business;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.group12.fitnics.objects.Notification;

public class NotificationBuilder extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notification = (Notification) intent.getSerializableExtra("Notification");

        NotificationHelper notificationHelper = new NotificationHelper(context,notification);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(notification);
        notificationHelper.getManager().notify(notification.getNotificationID(),nb.build());
    }
}
