package com.digigarson.digigarsonwaiter.BrowserSettings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.digigarson.digigarsonwaiter.JSInterfaces.AccountControl;

public class InitializeBrowser {


    private Context context = null;

    public InitializeBrowser(Context _window) {
        this.context = _window;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public WebView Initialize(WebView webView){
        // Set Web View Settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptThirdPartyCookies( webView, true );
        }
        // Set Javascript Interface Classes
        webView.addJavascriptInterface(new AccountControl(this.context), "Application_AccountControl");
        webView.addJavascriptInterface(new com.digigarson.digigarsonwaiter.JSInterfaces.Values(), "Application_Values");
        webView.addJavascriptInterface(new com.digigarson.digigarsonwaiter.JSInterfaces.Notifications(this.context), "Application_Notifications");
        // Set Browser Functions
        webView.setWebViewClient(new BrowserFunctions(this.context));
        // Set Browser URL
        webView.loadUrl(Values.getHomeURL());

        return webView;
    }
}
