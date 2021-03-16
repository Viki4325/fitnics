package com.group12.fitnics.objects;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.group12.fitnics.business.NotificationHelper;

public class Notification extends BroadcastReceiver {
    private String description;
    private String title;



    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(0,nb.build());
    }

    public Notification()
    {

    }

}
