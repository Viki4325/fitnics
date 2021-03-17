package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


import com.group12.fitnics.R;
import com.group12.fitnics.business.TimePickerFragment;
import com.group12.fitnics.objects.Notification;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateNotificationActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    TextView Alertbox;
    NotificationManagerCompat notificationManager;
    Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);
        String user = getIntent().getStringExtra("userID");

        Alertbox = (TextView) findViewById(R.id.AlertText);
        //Initializes the timer
        c = Calendar.getInstance();
        //opens timepicker when clicked
        Button buttonTimePicker = findViewById(R.id.timePicker);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        notificationManager = NotificationManagerCompat.from(this);
    }
    //the listener overrided so it can be used by the class
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        String timeText = "Time Set: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        Alertbox.setText(timeText);
    }
    //creates the alarm to go off every 24hours
    public void createNotificationOnClick(View v) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,Notification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        Alertbox.setText(timeText);
    }
    //deletes the alarm
    public void deleteAlarm(View v){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,Notification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.cancel(pendingIntent);
        Alertbox.setText("Alarm Deleted");
    }
}