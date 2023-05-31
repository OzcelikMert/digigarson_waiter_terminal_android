package com.digigarson.digigarsonwaiter.Updater;

public class Values {
    /* Version ID */
    private static int VersionCode = 0;
    public static int getVersionCode(){
        return VersionCode;
    }
    public static void setVersionCode(int versionCode){
        VersionCode = versionCode;
    }
    /* end Version ID */

    /* Version Name */
    private static String VersionName = "";
    public static String getVersionName(){
        return VersionName;
    }
    public static void setVersionName(String versionName){
        VersionName = versionName;
    }
    /* end Version Name */
}
