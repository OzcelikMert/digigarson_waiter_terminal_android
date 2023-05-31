package com.digigarson.digigarsonwaiter.JSInterfaces;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.digigarson.digigarsonwaiter.AccountControl.Classes.Accounts;
import com.digigarson.digigarsonwaiter.MatrixLibrary.LibraryMessage;
import com.digigarson.digigarsonwaiter.Notifications.Classes.NotificationInfos;
import com.digigarson.digigarsonwaiter.Notifications.OrderNotification;
import com.digigarson.digigarsonwaiter.Notifications.RequestNotification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;

public class Notifications {
    public Notifications(Context context){ this.context = context; }

    private String getTag() {return "JSInterfaces Notifications"; }

    private Context context = null;

    // Show New Order
    @JavascriptInterface
    public void showNewOrder(String orderInfos){
        try {
            OrderNotification orderNotification = new OrderNotification(this.context);
            Gson gson = new Gson();
            orderNotification.showNewOrder(gson.fromJson(orderInfos, NotificationInfos.class));
        }catch(Exception exception){ Log.e(getTag(), "Error showNewOrder: " + exception.toString()); }
    }

    // Show New Request
    @JavascriptInterface
    public void showNewRequest(String requestInfos){
        try {
            RequestNotification orderNotification = new RequestNotification(this.context);
            Gson gson = new Gson();
            orderNotification.showNewRequest(gson.fromJson(requestInfos, NotificationInfos.class));
        }catch(Exception exception){ Log.e(getTag(), "Error showNewRequest: " + exception.toString()); }
    }
}
