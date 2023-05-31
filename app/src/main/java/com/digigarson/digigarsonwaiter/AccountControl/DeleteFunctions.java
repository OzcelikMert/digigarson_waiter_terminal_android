package com.digigarson.digigarsonwaiter.AccountControl;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DeleteFunctions {
    private String getTag() {return "AccountControl Delete"; }

    // Delete Account
    public void deleteAccount(int accountID){
        try{
            SQLiteDatabase db = Values.getDatabase();
            db.delete(Values.getAccountsTableName(), Values.getColumnID() + " = ?", new String[] {String.valueOf(accountID)});
        }catch(Exception exception){ Log.e(getTag(), "Error deleteAccount: " + exception.toString()); }
    }

    // Delete All Table Sections
    public void deleteAllTableSections(){
        try{
            SQLiteDatabase db = Values.getDatabase();
            db.delete(Values.getTableSectionsTableName(), null, null);
        }catch(Exception exception){ Log.e(getTag(), "Error deleteAllTableSections: " + exception.toString()); }
    }
}
