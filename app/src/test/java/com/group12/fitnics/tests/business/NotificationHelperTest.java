package com.group12.fitnics.tests.business;

import com.group12.fitnics.R;
import com.group12.fitnics.business.NotificationHelper;
import com.group12.fitnics.presentation.MainActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.core.app.NotificationCompat;

public class NotificationHelperTest {

    private NotificationHelper notificationHelper;
    private Context context;
    private ContextWrapper wrapper;



    @BeforeClass
    public static void initialSetup(){
        System.out.println("\nStarted testing NotificationHelper from the Logic layer....");

    }

    @Before
    public void setup() {
        context = MainActivity.getAppContext();
        notificationHelper = new NotificationHelper(context);
    }

    @Test
    public void testGetChannelNotification(){
        NotificationCompat.Builder tChannel =  new NotificationCompat.Builder(context, notificationHelper.channelID).setContentTitle("Fitnics Alarm!").setContentText("Time to workout.").setSmallIcon(R.mipmap.ic_fitnics_logo_round);
        NotificationCompat.Builder mChannel = notificationHelper.getChannelNotification();
        assert(tChannel == mChannel);
    }

    @Test
    public void testGetManager(){
        System.out.println("\nStarting testGetManager");
        NotificationManager tManager = notificationHelper.getManager();
        NotificationManager mManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert (tManager == mManager);
    }
}