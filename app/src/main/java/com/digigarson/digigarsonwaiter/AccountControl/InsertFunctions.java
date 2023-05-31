package com.digigarson.digigarsonwaiter.AccountControl;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.digigarson.digigarsonwaiter.AccountControl.Classes.Accounts;

import java.util.List;

public class InsertFunctions {
    private String getTag() {return "AccountControl Insert"; }

    // Insert Account
    public void saveAccounts(List<Accounts> accounts){
        try {
            SQLiteDatabase db = Values.getDatabase();

            for (int index=0; index < accounts.size(); index++){
                ContentValues accountValues = new ContentValues();
                accountValues.put(Values.getColumnAccountName(), accounts.get(index).account_name);
                accountValues.put(Values.getColumnBranchCode(), accounts.get(index).branch_code);
                accountValues.put(Values.getColumnVerifyCode(), accounts.get(index).verify_code);
                accountValues.put(Values.getColumnIsActive(), "0");
                db.insert(Values.getAccountsTableName(), null, accountValues);
            }

        }catch(Exception exception){ Log.e(getTag(), "Error saveAccounts: " + exception.toString()); }
    }

    // Insert Table Sections
    public void saveTableSections(String[] tableSections, int accountID){
        try {
            SQLiteDatabase db = Values.getDatabase();

            for (String TableSection : tableSections){
                ContentValues tableSectionValues = new ContentValues();
                tableSectionValues.put(Values.getColumnID(), accountID);
                tableSectionValues.put(Values.getColumnTableSection(), TableSection);
                db.insert(Values.getTableSectionsTableName(), null, tableSectionValues);
            }
        }catch(Exception exception){ Log.e(getTag(), "Error saveTableSections: " + exception.toString()); }
    }
}
