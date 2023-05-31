package com.digigarson.digigarsonwaiter.Notifications;

public class Values {
    /* Notification ID Order */
    private static int NotificationIdOrder = 0;
    public static int getNotificationIdOrder(){
        return NotificationIdOrder;
    }
    public static void setNotificationIdOrder(int notificationId){
        NotificationIdOrder = notificationId;
    }
    /* end Notification ID Order */

    /* Notification ID Request */
    private static int NotificationIdRequest = 1;
    public static int getNotificationIdRequest(){
        return NotificationIdRequest;
    }
    public static void setNotificationIdRequest(int notificationId){
        NotificationIdRequest = notificationId;
    }
    /* end Notification ID Request */
}
