package com.digigarson.digigarsonwaiter.BrowserSettings;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserFunctions extends WebViewClient{

    private Context context = null;
    private boolean loadingFinished = true;
    private boolean redirect = false;

    public BrowserFunctions(Context _window) {
        this.context = _window;
    }


    public boolean shouldOverrideUrlLoading(WebView webView, String urlNewString) {
        if (!loadingFinished) {
            redirect = true;
        }

        loadingFinished = false;
        webView.loadUrl(urlNewString);
        return true;
    }

    public void onPageStarted(WebView webView, String url) {
        loadingFinished = false;
        //SHOW LOADING IF IT ISN'T ALREADY VISIBLE
    }

    public void onPageFinished(WebView webView, String url) {
        if (!redirect) {
            loadingFinished = true;
            //HIDE LOADING IT HAS FINISHED
        } else {
            redirect = false;
        }
    }
}
