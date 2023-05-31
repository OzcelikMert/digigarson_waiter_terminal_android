package com.digigarson.digigarsonwaiter.JSInterfaces;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.digigarson.digigarsonwaiter.MatrixLibrary.LibraryApplication;

public class Values {
    private String getTag() {return "JSInterfaces Values"; }

    /* Account Id */
    private int AccountID = 0;
    @JavascriptInterface
    public int getAccountID(){
        return this.AccountID;
    }
    @JavascriptInterface
    public void setAccountID(int accountID){
        Log.e(getTag(), "setAccountID: " + accountID);
        this.AccountID = accountID;
    }
    /* end Account Id */

    /* Application Version Name */
    @JavascriptInterface
    public String getVersionName(){
        String value = "";
        try {
            LibraryApplication libraryApplication = new LibraryApplication();
            value = libraryApplication.getApplicationVersionName();
        }catch(Exception exception){ Log.e(getTag(), "Error getVersionName: " + exception.toString()); }
        return value;
    }
    /* Application Version Name */
}
