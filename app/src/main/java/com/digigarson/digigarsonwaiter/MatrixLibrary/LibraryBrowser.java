package com.digigarson.digigarsonwaiter.MatrixLibrary;

import android.webkit.CookieManager;

public class LibraryBrowser {

    /* Get Cookie Value */
    public String GetCookie(String siteName,String CookieName){
        String CookieValue = null;

        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(siteName);
        if(cookies != null){
            String[] temp=cookies.split(";");
            for (String ar1 : temp ){
                if(ar1.contains(CookieName)){
                    String[] temp1=ar1.split("=");
                    CookieValue = temp1[1];
                }
            }
        }
        return CookieValue;
    }
    /* end Get Cookie Value */
}
