package com.example.moodmate2;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class NotificationUtils {
    private static final String CHANNEL_ID = "channel_id";
    private static final String CHANNEL_NAME = "Channel Name";
    private static final String CHANNEL_DESCRIPTION = "Channel Description";
    private static final int NOTIFICATION_ID = 1;

    public static void showNotification(Context context, String title, String message) {
        // Create a notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true);

        // Get the notification manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a notification channel (for Android Oreo and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(channel);
        }

        // Show the notification
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

