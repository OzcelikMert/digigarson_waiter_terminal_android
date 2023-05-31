package com.digigarson.digigarsonwaiter.AccountControl;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.digigarson.digigarsonwaiter.AccountControl.Classes.Accounts;

import java.util.List;

public class UpdateFunctions {
    private String getTag() {return "AccountControl Insert"; }

    // Change Account Is Active
    public void changeAccountIsActive(int accountID, String isActive){
        try {
            SQLiteDatabase db = Values.getDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Values.getColumnIsActive(), isActive);

            db.update(Values.getAccountsTableName(), contentValues,  Values.getColumnID() + " = ?", new String[] {String.valueOf(accountID)});
        }catch(Exception exception){ Log.e(getTag(), "Error changeAccountIsActive: " + exception.toString()); }
    }
}
