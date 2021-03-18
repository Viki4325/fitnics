package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


import com.group12.fitnics.R;
import com.group12.fitnics.objects.Notification;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateNotificationActivity extends AppCompatActivity {
    TimePicker picker;
    TextView Alertbox;
    Button save;
    NotificationManagerCompat notificationManager;
    Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);
        String user = getIntent().getStringExtra("userID");
        //creates popup window when the button is pressed
        Alertbox = (TextView) findViewById(R.id.AlertText);
        c = Calendar.getInstance();
        picker=(TimePicker)findViewById(R.id.timePicker);
        save = (Button) findViewById(R.id.savebtn);
        notificationManager = NotificationManagerCompat.from(this);
    }

    public void createNotificationOnClick(View v) {
        int hour, minute;
        if (Build.VERSION.SDK_INT >= 23 ){
            hour = picker.getHour();
            minute = picker.getMinute();
        }
        else{
            hour = picker.getCurrentHour();
            minute = picker.getCurrentMinute();
        }

        c.set(Calendar.HOUR_OF_DAY,hour);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,Notification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        Alertbox.setText(timeText);
    }

    public void deleteAlarmOnClick(View v){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,Notification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.cancel(pendingIntent);
        Alertbox.setText("Alarm Deleted");
    }
}