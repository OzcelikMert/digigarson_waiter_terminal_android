package com.digigarson.digigarsonwaiter.AccountControl;

import android.database.sqlite.SQLiteDatabase;

public class Values {
    /* Database Name */
    private static String DatabaseName = "Accounts.db";
    public static String getDatabaseName(){
        return DatabaseName;
    }
    /* end Database Name */

    /* Database Version */
    private static int DatabaseVersion = 1;
    public static int getDatabaseVersion(){
        return DatabaseVersion;
    }
    /* end Database Name */

    /* Account Database Connection */
    private static SQLiteDatabase Database = null;
    public static SQLiteDatabase getDatabase(){
        return Database;
    }
    public static void setDatabase(SQLiteDatabase database){
        Database = database;
    }
    /* end Account Database Connection */

    /* DB Values */
    private static String ColumnID = "id";
    public static String getColumnID(){
        return ColumnID;
    }
    // Accounts
    private static String AccountsTableName = "accounts";
    public static String getAccountsTableName(){
        return AccountsTableName;
    }
    private static String ColumnBranchCode = "branch_code";
    public static String getColumnBranchCode(){
        return ColumnBranchCode;
    }
    private static String ColumnVerifyCode = "verify_code";
    public static String getColumnVerifyCode(){
        return ColumnVerifyCode;
    }
    private static String ColumnAccountName = "account_name";
    public static String getColumnAccountName(){
        return ColumnAccountName;
    }
    private static String ColumnIsActive = "is_active";
    public static String getColumnIsActive(){
        return ColumnIsActive;
    }
    private static String CreateAccounts = "create table IF NOT EXISTS " + AccountsTableName

            + "("
            + ColumnID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ColumnAccountName + " TEXT, "
            + ColumnBranchCode + " TEXT, "
            + ColumnVerifyCode + " TEXT, "
            + ColumnIsActive + " TEXT"
            + ")";
    public static String getCreateAccounts(){
        return CreateAccounts;
    }
    private static String DropAccounts = "DROP TABLE IF EXISTS " + AccountsTableName;
    public static String getDropAccounts(){
        return DropAccounts;
    }
    // Sections
    private static String TableSectionsTableName = "table_sections";
    public static String getTableSectionsTableName(){
        return TableSectionsTableName;
    }
    private static String ColumnTableSection = "table_section";
    public static String getColumnTableSection(){
        return ColumnTableSection;
    }
    private static String CreateTableSections = "create table IF NOT EXISTS " + TableSectionsTableName
            + "("
            + ColumnID + " INTEGER, "
            + ColumnTableSection + " TEXT"
            + ")";
    public static String getCreateTableSections(){
        return CreateTableSections;
    }
    private static String DropTableSections = "DROP TABLE IF EXISTS " + TableSectionsTableName;
    public static String getDropTableSections(){
        return DropTableSections;
    }
    /* end DB Values */

}
