package com.example.moodmate2;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the broadcast is received

        // Set the title and message for the notification
        String title = "Mood Reminder";
        String message = "Remember to set your mood for today";

        // Call the NotificationUtils class to show the notification
        NotificationUtils.showNotification(context, title, message);
    }
}


