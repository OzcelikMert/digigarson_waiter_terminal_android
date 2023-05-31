package com.digigarson.digigarsonwaiter.AccountControl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.digigarson.digigarsonwaiter.AccountControl.Classes.Accounts;

import java.util.ArrayList;
import java.util.List;

public class SelectFunctions {
    private String getTag() {return "AccountControl Select"; }

    // Get Accounts
    public List<Accounts> getAccounts(){
        List<Accounts> values = new ArrayList<Accounts>();
        try{
            SQLiteDatabase db = Values.getDatabase();
            Cursor cursor = db.rawQuery("select * from " + Values.getAccountsTableName(), null);
            if(cursor != null){
                while(cursor.moveToNext()){
                    values.add(new Accounts(
                            cursor.getInt(cursor.getColumnIndex(Values.getColumnID())),
                            cursor.getString(cursor.getColumnIndex(Values.getColumnAccountName())),
                            cursor.getString(cursor.getColumnIndex(Values.getColumnBranchCode())),
                            cursor.getString(cursor.getColumnIndex(Values.getColumnVerifyCode()))
                    ));
                }
                cursor.close();
            }
        }catch(Exception exception){ Log.e(getTag(), "Error getAccounts: " + exception.toString()); }

        return values;
    }

    // Get Table Section
    public ArrayList<String> getTableSections(int accountID){
        ArrayList<String> values = new ArrayList<String>();
        try{
            SQLiteDatabase db = Values.getDatabase();

            Cursor cursor = db.rawQuery("select * from " + Values.getTableSectionsTableName()
                    + " where "
                    + Values.getColumnID()
                    + " = ?", new String[] {String.valueOf(accountID)}
            );
            if(cursor != null){
                while(cursor.moveToNext()){
                    Log.e(getTag(), "Error getTableSections: TableSection: " + cursor.getString(cursor.getColumnIndex(Values.getColumnTableSection())));
                    values.add(
                            cursor.getString(cursor.getColumnIndex(Values.getColumnTableSection()))
                    );
                }
                cursor.close();
            }
        }catch(Exception exception){ Log.e(getTag(), "Error getTableSections: " + exception.toString()); }

        return values;
    }

    // Check Account
    public boolean checkAccount(String branchCode, String verifyCode){
        boolean value = false;
        try{
            SQLiteDatabase db = Values.getDatabase();
            Cursor cursor = db.rawQuery("select * from " + Values.getAccountsTableName()
                    + " where "
                    + Values.getColumnBranchCode() + " = ? and "
                    + Values.getColumnVerifyCode() + " = ?", new String[]{branchCode, verifyCode});
            if(cursor != null){
                if(cursor.moveToFirst())
                    value = true;
                cursor.close();
            }
        }catch(Exception exception){ Log.e(getTag(), "Error checkAccounts: " + exception.toString()); }

        return value;
    }

    // Check Table Section
    public boolean checkTableSection(String tableSection, int accountID){
        boolean value = false;
        try{
            SQLiteDatabase db = Values.getDatabase();
            Cursor cursor = db.rawQuery("select * from " + Values.getTableSectionsTableName()
                    + " where "
                    + Values.getColumnID() + " = ? and "
                    + Values.getColumnTableSection() + " = ?", new String[]{String.valueOf(accountID), tableSection});
            if(cursor != null){
                if(cursor.moveToFirst())
                    value = true;
                cursor.close();
            }
        }catch(Exception exception){ Log.e(getTag(), "Error checkTableSection: " + exception.toString()); }

        return value;
    }

    // Get Activated Account
    public List<Accounts> getActivatedAccount(){
        List<Accounts> values = new ArrayList<Accounts>();
        try{
            SQLiteDatabase db = Values.getDatabase();
            Cursor cursor = db.rawQuery("select * from " + Values.getAccountsTableName() + " where " + Values.getColumnIsActive() + "='1'", null);
            if(cursor != null){
                if(cursor.moveToNext()){
                    values.add(new Accounts(
                            cursor.getInt(cursor.getColumnIndex(Values.getColumnID())),
                            cursor.getString(cursor.getColumnIndex(Values.getColumnAccountName())),
                            cursor.getString(cursor.getColumnIndex(Values.getColumnBranchCode())),
                            cursor.getString(cursor.getColumnIndex(Values.getColumnVerifyCode()))
                    ));
                }
                cursor.close();
            }
        }catch(Exception exception){ Log.e(getTag(), "Error getAccounts: " + exception.toString()); }

        return values;
    }
}
