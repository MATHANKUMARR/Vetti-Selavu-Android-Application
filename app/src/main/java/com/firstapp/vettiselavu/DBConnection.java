package com.firstapp.vettiselavu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {

    Context context;

    public DBConnection(Context context) {
        super(context, "Money.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table savemoney (ROWID INTEGER primary key AUTOINCREMENT,mode TEXT, amount TEXT, reason TEXT, description TEXT, date_time NOT NULL DEFAULT CURRENT_TIMESTAMP)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists savemoney");
    }

    public Boolean insertData(String mode, String amount, String reason, String description){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mode",mode);
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("description",description);

        long result = DB.insert("savemoney",null,contentValues);

        return result != -1;
    }

    public Cursor getData(){

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from savemoney",null);

        return cursor;
    }
}
