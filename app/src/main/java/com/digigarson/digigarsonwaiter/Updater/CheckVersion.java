package com.digigarson.digigarsonwaiter.Updater;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ContextThemeWrapper;

import com.digigarson.digigarsonwaiter.MatrixLibrary.LibraryApplication;
import com.digigarson.digigarsonwaiter.MatrixLibrary.LibraryMessage;
import com.digigarson.digigarsonwaiter.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CheckVersion {
    public CheckVersion(Context context){ this.context = context; }

    private String getTag() {return "Updater checkVersion"; }

    private Context context;

    // Check Version
    @SuppressLint("StaticFieldLeak")
    public class Check extends AsyncTask<String,Void,Void> {

        private ProgressDialog progressDialog = new ProgressDialog(new ContextThemeWrapper(context, R.style.Theme_AppCompat_DayNight_Dialog));

        @Override
        protected void onPreExecute() {
            // Start
            progressDialog.setCancelable(false);
            progressDialog.setMessage(context.getString(R.string.waiting));
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... values) {
            try {
                getVersions();
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Finish
            progressDialog.dismiss();
            progressDialog = null;
            UpdateMessage();
        }

    }

    // Get Versions
    private void getVersions() {
        try {
            String URL = "https://digigarson.com/dg/waiter_application/version/android/get/index.php";

            // URL Connection
            java.net.URL url = new URL(URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            // Get Values
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));

            String result="";
            String line="";

            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            // end Get Values

            httpURLConnection.disconnect();
            // end URL Connection

            Log.e(getTag(), "Version Result: " + result);

            int version_code = 1;
            String version_name = "1.0.0";

            // Json Result
            if(!result.equals("")){
                JSONObject responseJSON = new JSONObject(result);
                version_code = responseJSON.getInt("version_code");
                version_name = responseJSON.getString("version_name");
            }
            // end Json Result

            Values.setVersionCode(version_code);
            Values.setVersionName(version_name);

            Log.e(getTag(), "Version Code: " + Values.getVersionCode() + " | VersionName: " + Values.getVersionName());
        } catch (MalformedURLException e) {
            Log.e(getTag(), "Error-1 " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(getTag(), "Error-2 " + e.getMessage());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(getTag(), "Error-3 " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update Message
    private void UpdateMessage(){
        LibraryApplication libraryApplication =  new LibraryApplication();
        LibraryMessage libraryMessage =  new LibraryMessage();
        // Check Version
        if(libraryApplication.getApplicationVersionID() != Values.getVersionCode()){
            // Set Message
            libraryMessage.GetAlertDialog(context,
                    context.getString(R.string.update_message_title),
                    context.getString(R.string.update_message) + "(v" + Values.getVersionName() + ")",
                    false,
                    true,
                    context.getString(R.string.update),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Try Again Button
                            dialog.dismiss();
                            // Update
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            //http://play.google.com/store/apps/details?id=<package_name>
                            intent.setData(Uri.parse("market://details?id=com.digigarson.digigarsonwaiter"));
                            context.startActivity(intent);
                            ((Activity)context).finish();
                        }
                    },
                    true,
                    context.getString(R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Cancel Button
                            ((Activity)context).finish();
                        }
                    });
            // end Set Message
        }
        // end Check Version
    }
}
