package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TimePicker;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessNotification;
import com.group12.fitnics.business.AccessNotificationLogs;
import com.group12.fitnics.business.NotificationBuilder;
import com.group12.fitnics.objects.Notification;
import com.group12.fitnics.objects.NotificationLog;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.presentation.adapters.NotificationLogItemAdapter;


import java.util.Calendar;

public class CreateNotificationActivity extends AppCompatActivity {

    TimePickerDialog picker;
    ListView NotificationListView;
    FloatingActionButton add;
    private AccessNotificationLogs log;
    Calendar c;
    User selectedUser;
    int size;
    AccessNotification accessNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        add = (FloatingActionButton) findViewById(R.id.addNotificationBtn);
        log = new AccessNotificationLogs();
        accessNotification = new AccessNotification();

        getUserLoggedIn();
        setupList();
        setupPickerOnClickListener();
        setupListOnClickListener();
    }

    private void setupList(){
        NotificationListView = (ListView) findViewById(R.id.NotificationList);
        NotificationLogItemAdapter adapter = (NotificationLogItemAdapter) new NotificationLogItemAdapter(getApplicationContext(),0,log.getNotificationLogByUser(selectedUser.getUserID()));
        NotificationListView.setAdapter(adapter);
        size = NotificationListView.getAdapter().getCount();
    }

    private void setupListOnClickListener() {
        NotificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //To get the exercise we have selected
                NotificationLog selectNotificationLog = (NotificationLog) (NotificationListView.getItemAtPosition(position));

                Intent showDetail = new Intent(getApplicationContext(), IndividualNotificationActivity.class);
                showDetail.putExtra("user", selectedUser);
                showDetail.putExtra("NotificationLogSelected",  selectNotificationLog);
                startActivity(showDetail);

            }
        });
    }

    private void setupPickerOnClickListener()
    {
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int min = c.get(Calendar.MINUTE);
                //time picker dialog
                picker = new TimePickerDialog(CreateNotificationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        createNotification(hourOfDay,minute,"Temp");
                    }
                }, hour, min, false);
                picker.show();
            }
        });
    }

    //when a notification is created create a notification log and save them in the persistence then place the log in the list
    public void createNotification(int hour, int min,String title) {
        c.set(Calendar.HOUR_OF_DAY,hour);
        c.set(Calendar.MINUTE,min);
        c.set(Calendar.SECOND,0);

        Notification notify = new Notification("Notification "+size,hour,min,true);
        notify.setNotificationID();
        NotificationLog notifyLog = new NotificationLog(title,selectedUser.getUserID(),notify.getNotificationID(),hour,min);
        accessNotification.insertNotification(notify);
        log.insertNotificationLog(notifyLog);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationBuilder.class);
        intent.putExtra("Notification", notify);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        finish();
        startActivity(getIntent());
    }

    // when deleting a notification pass the log find the notification that matches it and delete the notification then remove both from their respective persistence's and then the list
    public void deleteAlarm(NotificationLog notifyLog){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,NotificationBuilder.class);

        Notification notify = accessNotification.getNotificationById(notifyLog.getNotificationID());
        log.deleteNotificationLog(notifyLog.getUserID(),notifyLog.getNotificationID());
        accessNotification.deleteNotificationById(notify.getNotificationID());

        intent.putExtra("Notification", notify);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.cancel(pendingIntent);
    }

    private void getUserLoggedIn(){
        selectedUser = (User) getIntent().getSerializableExtra("userLoggedIn");
        if(selectedUser == null){
            selectedUser = new User();
            selectedUser.setUserID(-1);
        }
    }

    public void backOnClick(View v){
        Intent back = new Intent(getApplicationContext(), HomeActivity.class);
        back.putExtra("userLoggedIn", selectedUser);
        back.putExtra("username",selectedUser.getUsername());
        startActivity(back);
    }

    /*
     * Updates info on this page
     * */
    @Override
    protected void onResume() {
        super.onResume();
        setupList();
    }
}