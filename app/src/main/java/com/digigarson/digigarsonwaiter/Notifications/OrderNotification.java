package com.digigarson.digigarsonwaiter.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.digigarson.digigarsonwaiter.MatrixLibrary.LibraryMessage;
import com.digigarson.digigarsonwaiter.Notifications.Classes.NotificationInfos;
import com.digigarson.digigarsonwaiter.R;

public class OrderNotification {
    public OrderNotification(Context context){ this.context = context; }

    private String getTag() {return "Notifications OrderNotification"; }

    // Library Message
    private LibraryMessage libraryMessage = new LibraryMessage();

    private Context context = null;

    // Show New Order
    public void showNewOrder(NotificationInfos notificationInfos){
        int Notification_Id = Values.getNotificationIdOrder() + 1;
        try {
            /* Set Notification */
            NotificationManager mNotificationManager =
                    (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);

            final Notification.Builder builder = libraryMessage.StartNotification(this.context,
                    this.context.getClass(),
                    R.mipmap.digigarson_icon,
                    notificationInfos.title,
                    notificationInfos.message,
                    true,
                    new long[]{1000, 1000, 1000, 1000},
                    true,
                    new Integer[]{Color.GREEN, 3000, 3000},
                    Settings.System.DEFAULT_NOTIFICATION_URI);

            // Start New Notification
            assert mNotificationManager != null;

            // Create Notification Channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = String.valueOf(Notification_Id);
                String OldChannelId = String.valueOf(Values.getNotificationIdOrder());

                // Clear Notification Channel
                mNotificationManager.deleteNotificationChannel(OldChannelId);
                // end Clear Notification Channel
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Digigarson Waiter Notification Service",
                        NotificationManager.IMPORTANCE_HIGH);
                channel.enableVibration(true);
                mNotificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }
            // end Create Notification Channel

            // Clear Notification Manager
            mNotificationManager.cancel(Values.getNotificationIdOrder());
            // end Clear Notification Manager
            mNotificationManager.notify(Notification_Id, builder.build());
            // end Start New Notification

            Values.setNotificationIdOrder(Notification_Id);
            /* end Set Notification */
        }catch(Exception exception){ Log.e(getTag(), "Error showNewOrder: " + exception.toString()); }
    }
}
