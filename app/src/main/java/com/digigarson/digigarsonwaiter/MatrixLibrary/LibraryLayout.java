package com.digigarson.digigarsonwaiter.MatrixLibrary;

import android.content.Context;
import android.content.Intent;

public class LibraryLayout {

    /* Change Layout */
    public void ChangeLayout(Context context, Class aClass){
        Intent intent = new Intent(context, aClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
    /* end Change Layout */
}
