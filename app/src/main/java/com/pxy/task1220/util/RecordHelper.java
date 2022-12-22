package com.pxy.task1220.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RecordHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Ninja.db";
    private static final String TABLE_USER= "user";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_Password = "password";
    private static final String COLUMN_encrypted= "encrypted";
    private static final String COLUMN_Answer= "Answer";
    private static final int DATABASE_VERSION = 1;
    public static final String CREATE_HISTORY = "CREATE TABLE "
            + TABLE_USER
            + " ("
            + " " + COLUMN_NAME + " text,"
            + " " + COLUMN_Password + " text,"
            + " " + COLUMN_encrypted + " text,"
            + " " + COLUMN_Answer + " text"
            + ")";

    public RecordHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //    RecordHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
