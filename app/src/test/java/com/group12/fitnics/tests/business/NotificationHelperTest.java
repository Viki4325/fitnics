package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.NotificationHelper;
import com.group12.fitnics.presentation.MainActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import android.content.Context;

public class NotificationHelperTest {

    private NotificationHelper notificationHelper;
    private Context context;



    @BeforeClass
    public static void initialSetup(){
        System.out.println("\nStarted testing NotificationHelper from the Logic layer....");

    }

    @Before
    public void setup() {

        notificationHelper = new NotificationHelper(context);
    }

    @Test
    public void getChannelNotification(){

    }

    @Test
    public void getManager(){

    }
}