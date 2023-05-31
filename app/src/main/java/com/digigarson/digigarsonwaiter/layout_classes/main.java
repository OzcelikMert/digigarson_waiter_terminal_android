package com.digigarson.digigarsonwaiter.layout_classes;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.digigarson.digigarsonwaiter.AccountControl.CreateDB;
import com.digigarson.digigarsonwaiter.R;

import com.digigarson.digigarsonwaiter.BrowserSettings.InitializeBrowser;
import com.digigarson.digigarsonwaiter.MatrixLibrary.LibraryMessage;
import com.digigarson.digigarsonwaiter.MatrixLibrary.LibraryMobile;
import com.digigarson.digigarsonwaiter.Updater.CheckVersion;

import java.util.Objects;

public class main extends AppCompatActivity {
    private String getTag() {return "layout_classes main"; }

    private LibraryMessage libraryMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Close Action Bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        // Close Keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // Check Mobile Internet
        if(checkInternet())
            return;
        // Get Permissions
        getPermissions();
        // Check Application Version
        checkApplicationVersion();
        // Set Account Control DB
        setAccountControlDB();
        // Set Web View Values
        startBrowser();
    }

    @Override
    public void onBackPressed() {
        try{
            showExitMessage();
        }catch(Exception exception){ Log.e(getTag(), "Error onBackPressed: " + exception.toString()); }
    }

    /* Browser Functions */
    private void startBrowser() {
        try {
            WebView digigarsonWaiter_Browser = (WebView) findViewById(R.id.DigigarsonWaiter_Browser);
            // Initialize Browser
            InitializeBrowser initializeBrowser = new InitializeBrowser(this);
            digigarsonWaiter_Browser = initializeBrowser.Initialize(digigarsonWaiter_Browser);
        } catch (Exception exception) {
            libraryMessage = new LibraryMessage();
            libraryMessage.GetAlertDialog(
                    this,
                    getString(R.string.start_browser_title),
                    getString(R.string.start_browser) + " ErrorInfo" + exception.toString(),
                    false,
                    true,
                    getString(R.string.okay),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            dialog.dismiss();
                        }
                    },
                    false,
                    null,
                    null

            );
        }
    }
    /* end Browser Functions */

    /* Check Mobile Internet */
    private boolean checkInternet(){
        boolean value = false;
        LibraryMobile libraryMobile = new LibraryMobile();
        if(!libraryMobile.CheckInternetConnection(this)){
            libraryMessage = new LibraryMessage();
            libraryMessage.GetAlertDialog(this,
                    getString(R.string.no_internet),
                    getString(R.string.no_internet_check),
                    false,
                    true,
                    getString(R.string.okay),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            dialog.dismiss();
                        }
                    },
                    false,
                    null,
                    null
            );
            value = true;
        }

        return value;
    }
    /* end Check Mobile Internet */

    /* Set Account Control DB */
    private void setAccountControlDB(){
        try{
            CreateDB createDB = new CreateDB(this);
        }catch(Exception exception){ Log.e(getTag(), "Error setAccountControlDB: " + exception.toString()); }
    }
    /* end Set Account Control DB */

    /* Permissions */
    public void getPermissions(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }

    }

    private void requestPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.VIBRATE, Manifest.permission.ACCESS_NOTIFICATION_POLICY}, 100);
        }

    }
    /* end Permissions */

    /* Check Application Version */
    private void checkApplicationVersion(){
        CheckVersion checkVersion = new CheckVersion(this);
        checkVersion.new Check().execute();
    }
    /* end Check Application Version */

    /* Exit Message */
    private void showExitMessage(){
        libraryMessage = new LibraryMessage();
        libraryMessage.GetAlertDialog(this,
                this.getString(R.string.exit_message_title),
                this.getString(R.string.exit_message),
                false,
                true,
                this.getString(R.string.exit),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                },
                true,
                this.getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel Button
                    }
                });
    }
    /* end Exit Message */
}
