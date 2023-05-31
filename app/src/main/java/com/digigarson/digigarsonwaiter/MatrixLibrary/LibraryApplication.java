package com.digigarson.digigarsonwaiter.MatrixLibrary;

import android.content.Context;

import com.digigarson.digigarsonwaiter.BuildConfig;

public class LibraryApplication {

    /* Get App Version ID */
    public int getApplicationVersionID(){
        int version_id = 0;
        try {
            // Get Version
            version_id = BuildConfig.VERSION_CODE;
            // end Get Version
        }catch(Exception e){
            e.printStackTrace();
        }

        return version_id;
    }
    /* end Get App Version ID */

    /* Get App Version Name */
    public String getApplicationVersionName(){
        String version_name = "1.0.0";
        try {
            // Get Version
            version_name = BuildConfig.VERSION_NAME;
            // end Get Version
        }catch(Exception e){
            e.printStackTrace();
        }

        return version_name;
    }
    /* end Get App Version Name */
}
