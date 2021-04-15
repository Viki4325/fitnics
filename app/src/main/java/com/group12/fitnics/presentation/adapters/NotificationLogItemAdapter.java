package com.group12.fitnics.presentation.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExercises;
import com.group12.fitnics.business.AccessNotification;
import com.group12.fitnics.business.UnitConverter;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.Notification;
import com.group12.fitnics.objects.NotificationLog;

import java.util.List;

public class NotificationLogItemAdapter extends ArrayAdapter<NotificationLog> {

     AccessNotification accessNotification= new AccessNotification();

    public NotificationLogItemAdapter(Context context, int resource, List<NotificationLog> notificationLogs) {
        super(context, resource, notificationLogs);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position , View convertView, ViewGroup parent) {

        NotificationLog notificationLog = getItem(position);
        //Should we make AccessNotification object or is there another way???
        System.out.println(notificationLog.getNotificationID());
        Notification notification = accessNotification.getNotificationById(notificationLog.getNotificationID());

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.notification_cell, parent, false);
        }

        TextView notificationTitle = convertView.findViewById(R.id.NotificationTitle);
        TextView notificationTime = convertView.findViewById(R.id.NotificationTime);
        TextView activate = convertView.findViewById(R.id.ActiveBox);

        notificationTitle.setText(notification.getTitle());
        if(notification.getMinute() >= 10) {
            notificationTime.setText(UnitConverter.convert12hour(notification.getHour()) + ":" + notification.getMinute() + " " + UnitConverter.am_pm(notification.getHour()));
        }else{
            notificationTime.setText(UnitConverter.convert12hour(notification.getHour()) + ":0" + notification.getMinute() + " " + UnitConverter.am_pm(notification.getHour()));
        }
        if(notification.isActive()) {
            activate.setText("Active");
        }else{
            activate.setText("Not Active");
        }



        return convertView;
    }
}
