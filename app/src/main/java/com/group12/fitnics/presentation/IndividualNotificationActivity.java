package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessNotification;
import com.group12.fitnics.business.AccessNotificationLogs;
import com.group12.fitnics.business.NotificationBuilder;
import com.group12.fitnics.objects.Notification;
import com.group12.fitnics.objects.NotificationLog;

import org.w3c.dom.Text;

import java.util.Calendar;

public class IndividualNotificationActivity extends AppCompatActivity {
    TimePicker picker;
    EditText title;
    Calendar c;
    String user;
    NotificationLog notificationLog;
    Notification notification;
    AccessNotification accessNotification;
    AccessNotificationLogs accessNotificationLogs;
    Switch activate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_notification);

        user = getIntent().getStringExtra("user");
        notificationLog = (NotificationLog) getIntent().getSerializableExtra("NotificationLogSelected");
        accessNotification = new AccessNotification();
        accessNotificationLogs = new AccessNotificationLogs();
        notification = accessNotification.getNotificationById(notificationLog.getNotificationID());

        c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,notificationLog.getHour());
        c.set(Calendar.MINUTE,notificationLog.getMinutes());
        c.set(Calendar.SECOND,0);
        picker = (TimePicker) findViewById(R.id.timePicker);
        title = (EditText) findViewById(R.id.TitleBox);
        title.setText(notificationLog.getTitle());
        activate = (Switch) findViewById(R.id.ActivateAlarmSwitch);
        activate.setChecked(notification.isActive());

    }

    //this is an update so it needs to be deleted then inserted
    public void saveNotificationOnClick(View v){
        deleteAlarmOnClick(v);

        int hour =  c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        c.set(Calendar.SECOND,0);

        //update title,hour,min,activate for Notification
        notification.setTitle(title.getText().toString());
        notification.setHour(hour);
        notification.setMinute(min);
        notification.setIsActive(activate.isChecked());
        //update title,hour,min for NotificationLog
        notificationLog.setTitle(title.getText().toString());
        notificationLog.setHour(hour);
        notificationLog.setMinutes(min);

        accessNotification.insertNotification(notification);
        accessNotificationLogs.insertNotificationLog(notificationLog);

        if(notification.isActive()) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, NotificationBuilder.class);
            intent.putExtra("Notification", notification);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            alarmManager.setRepeating(alarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        return;
    }

    public void deleteAlarmOnClick(View v){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationBuilder.class);

        Notification notify = accessNotification.getNotificationById(notificationLog.getNotificationID());
        accessNotificationLogs.deleteNotificationLog(notificationLog.getUserID(),notificationLog.getNotificationID());
        accessNotification.deleteNotificationById(notify.getNotificationID());

        intent.putExtra("Notification", notify);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.cancel(pendingIntent);
        return;
    }
}