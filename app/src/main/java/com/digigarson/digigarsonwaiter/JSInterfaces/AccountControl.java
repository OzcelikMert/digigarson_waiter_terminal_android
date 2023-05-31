package com.digigarson.digigarsonwaiter.JSInterfaces;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.digigarson.digigarsonwaiter.AccountControl.Classes.Accounts;
import com.digigarson.digigarsonwaiter.AccountControl.DeleteFunctions;
import com.digigarson.digigarsonwaiter.AccountControl.InsertFunctions;
import com.digigarson.digigarsonwaiter.AccountControl.SelectFunctions;
import com.digigarson.digigarsonwaiter.AccountControl.UpdateFunctions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class AccountControl {
    public AccountControl(Context context){ this.context = context; }

    private String getTag() {return "JSInterfaces AccountControl"; }

    // Insert Functions
    private InsertFunctions insertFunctions = new InsertFunctions();
    // Select Functions
    private SelectFunctions selectFunctions = new SelectFunctions();
    // Delete Functions
    private DeleteFunctions deleteFunctions = new DeleteFunctions();
    // Update Functions
    private UpdateFunctions updateFunctions = new UpdateFunctions();

    private Context context = null;

    // Get Accounts
    @JavascriptInterface
    public String getAccounts(){
        String values = "";
        try {
            List<Accounts> accounts = selectFunctions.getAccounts();
            if (accounts.size() > 0) {
                Gson gsonBuilder = new GsonBuilder().create();
                values = gsonBuilder.toJson(accounts);
            }
        }catch(Exception exception){ Log.e(getTag(), "Error getAccounts: " + exception.toString()); }

        return values;
    }

    // Get Table Sections
    @JavascriptInterface
    public String getTableSections(int accountID){
        String values = "{}";
        try {
            List<String> tableSections = selectFunctions.getTableSections(accountID);
            if(tableSections.size() > 0){
                Gson gsonBuilder = new GsonBuilder().create();
                values = gsonBuilder.toJson(tableSections);
            }
        }catch(Exception exception){ Log.e(getTag(), "Error getTableSections: " + exception.toString()); }

        return values;
    }

    // Get Activated Accounts
    @JavascriptInterface
    public String getActivatedAccount(){
        String values = "";
        try {
            List<Accounts> account = selectFunctions.getActivatedAccount();
            if (account.size() > 0) {
                Gson gsonBuilder = new GsonBuilder().create();
                values = gsonBuilder.toJson(account);
            }
        }catch(Exception exception){ Log.e(getTag(), "Error getActivatedAccount: " + exception.toString()); }
        return values;
    }

    // Save Account
    @JavascriptInterface
    public void saveAccount(String accountName, String branchCode, String verifyCode){
        try {
            if(!selectFunctions.checkAccount(branchCode, verifyCode)){
                List<Accounts> accounts = new ArrayList<Accounts>();
                accounts.add(new Accounts(0, accountName, branchCode, verifyCode));
                insertFunctions.saveAccounts(accounts);
            }
        }catch(Exception exception){ Log.e(getTag(), "Error saveAccounts: " + exception.toString()); }
    }

    // Save Table Sections
    @JavascriptInterface
    public void saveTableSections(String[] tableSections, int accountID){
        try {
            deleteFunctions.deleteAllTableSections();
            insertFunctions.saveTableSections(tableSections, accountID);
        }catch(Exception exception){ Log.e(getTag(), "Error saveTableSections: " + exception.toString()); }
    }

    // Delete Account
    @JavascriptInterface
    public void deleteAccount(int accountID){
        try {
            deleteFunctions.deleteAccount(accountID);
        }catch(Exception exception){ Log.e(getTag(), "Error deleteAccounts: " + exception.toString()); }
    }

    // Delete All Table Sections
    @JavascriptInterface
    public void deleteAllTableSections(){
        try {
            deleteFunctions.deleteAllTableSections();
        }catch(Exception exception){ Log.e(getTag(), "Error deleteAccounts: " + exception.toString()); }
    }

    // Change Account Is Active
    @JavascriptInterface
    public void changeAccountIsActive(int accountID, String isActive){
        try {
            updateFunctions.changeAccountIsActive(accountID, isActive);
        }catch(Exception exception){ Log.e(getTag(), "Error deleteAccounts: " + exception.toString()); }
    }
}
