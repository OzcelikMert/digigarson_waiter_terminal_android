package com.digigarson.digigarsonwaiter.MatrixLibrary;

import android.content.Context;
import android.net.ConnectivityManager;

public class LibraryMobile {

    /* Check Internet Connection */
    public Boolean CheckInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    /* end Check Internet Connection */
}
