package com.group12.fitnics.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.business.UnitConverter;
import com.group12.fitnics.objects.Notification;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification> {

    public NotificationAdapter(Context context, int resource, List<Notification> notificationList){
        super(context,resource,notificationList);
    }

    @Override
    public View getView(int position  , View convertView, ViewGroup parent) {

        Notification notification = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.notification_cell, parent, false);
        }

        TextView notificationTitle = convertView.findViewById(R.id.NotificationTitle);
        TextView notificationTime = convertView.findViewById(R.id.NotificationTime);
        EditText activate = convertView.findViewById(R.id.ActiveBox);

        notificationTitle.setText(notification.getTitle());

        notificationTime.setText(UnitConverter.convert12hour(notification.getHour())+":"+notification.getMinute()+" "+UnitConverter.am_pm(notification.getHour()));
        if(notification.isActive()){
            activate.setText("Active");
        }else{
            activate.setText("Not Active");
        }

        return convertView;
    }
}
