package com.group12.fitnics.business;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import com.group12.fitnics.objects.Notification;

import androidx.core.app.NotificationCompat;

import com.group12.fitnics.R;

public class NotificationHelper extends ContextWrapper
{
    private NotificationManager mManager;

    public NotificationHelper(Context base, Notification notify) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(notify);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel(Notification notify) {
        NotificationChannel channel = new NotificationChannel("Channel"+notify.getNotificationID(), notify.getTitle(), NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(Notification notify) {
        return new NotificationCompat.Builder(getApplicationContext(), "Channel"+notify.getNotificationID())
                .setContentTitle(notify.getTitle())
                .setContentText("Your alarm was set Fitnics needs you")
                .setSmallIcon(R.mipmap.ic_fitnics_logo_round);
    }

}
