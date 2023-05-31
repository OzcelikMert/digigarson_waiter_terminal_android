package com.digigarson.digigarsonwaiter.MatrixLibrary;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.ContextThemeWrapper;

import com.digigarson.digigarsonwaiter.R;

public class LibraryMessage {

    /* Get Alert Dialog */
    public void GetAlertDialog(Context context, String title, String message, Boolean cancelable, Boolean positiveButton, String positiveButtonText, DialogInterface.OnClickListener positiveFunction, Boolean negativeButton, String negativeButtonText, DialogInterface.OnClickListener negativeFunction){
        // Create Dialog Message
        AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.Theme_AppCompat_DayNight_Dialog));

        // Set Info
        dialog.setMessage(message)
                .setTitle(title)
                .setCancelable(cancelable);

        // Set Positive Button
        if(positiveButton){
            dialog.setPositiveButton(positiveButtonText, positiveFunction);
        }

        // Set Negative Button
        if(negativeButton){
            dialog.setNegativeButton(negativeButtonText, negativeFunction);
        }

        // Show Dialog
        dialog.create().show();
    }
    /* end Get Alert Dialog */

    /* Set Notification */
    public Notification.Builder StartNotification(Context context, Class aClass, Integer icon, String title, String contentText, Boolean vibration, long[] vibrationPattern, Boolean led, Integer[] lightsValues, Uri sound){
        // Create Notification
        Notification.Builder builder = new Notification.Builder(context);
        // end Create Notification

        /*// Intent Settings
        Intent intent = new Intent(context, aClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        // end Intent Settings*/

        // Notification Settings
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setWhen(System.currentTimeMillis());
        //builder.setContentIntent(pendingIntent);
        builder.setContentTitle(title);
        builder.setSmallIcon(icon);
        builder.setContentText(contentText);
        builder.setAutoCancel(false);
        builder.setOngoing(false);
        builder.setSound(sound);
        builder.setVibrate((vibration) ? vibrationPattern : null);
        if(led){
            builder.setLights(lightsValues[0], lightsValues[1], lightsValues[2]);
        }
        // end Notification Settings

        // Return
        return builder;
    }
    /* end Set Notification */
}
