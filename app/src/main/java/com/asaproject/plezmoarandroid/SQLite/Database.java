package com.asaproject.plezmoarandroid.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ARApp";
    private static final int DATABASE_VERSION = 1;

    public static final String MODEL_TABLE = "model";
    public static final String PARTS_TABLE = "parts";

    public static final String MODEL_ID = "model_id";
    public static final String MODEL_NAME = "model_name";
    public static final String ARDATA = "ar_data";
    public static final String MAIN_IMG = "main_img";

    public static final String PART_ID = "part_id";
    public static final String PART_NAME = "part_name";

    public static final String CREATE_MODEL_TABLE = "CREATE TABLE "
            + MODEL_TABLE + "(" + MODEL_ID + " TEXT PRIMARY KEY, "
            + MODEL_NAME + " TEXT, " + ARDATA + "TEXT ," + MAIN_IMG + "TEXT "+ ")";


    public static final String CREATE_PARTS_TABLE = "CREATE TABLE "
            + PARTS_TABLE + "(" + PART_ID + " TEXT PRIMARY KEY,"
            + PART_NAME + "TEXT ," + MODEL_ID + " INT, "
            + "FOREIGN KEY(" + MODEL_ID + ") REFERENCES "
            + MODEL_TABLE + "(model_id) " + ")";

    private static Database instance;

    public static synchronized Database getHelper(Context context) {
        if (instance == null)
            instance = new Database(context);
        return instance;
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MODEL_TABLE);
        db.execSQL(CREATE_PARTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
