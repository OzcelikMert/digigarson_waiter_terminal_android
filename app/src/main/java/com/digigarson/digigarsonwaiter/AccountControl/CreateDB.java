package com.digigarson.digigarsonwaiter.AccountControl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDB extends SQLiteOpenHelper {

    public CreateDB(@Nullable Context context) {
        super(context, Values.getDatabaseName(), null, Values.getDatabaseVersion());
        Values.setDatabase(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Accounts
        db.execSQL(Values.getCreateAccounts());
        // Sections
        db.execSQL(Values.getCreateTableSections());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Accounts
        db.execSQL(Values.getDropAccounts());
        // Sections
        db.execSQL(Values.getDropTableSections());

        onCreate(db);
    }
}
