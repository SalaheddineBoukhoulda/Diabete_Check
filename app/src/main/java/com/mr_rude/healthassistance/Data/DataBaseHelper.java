package com.mr_rude.healthassistance.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mr_Rude on 16/06/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "diabete.db";


    private static final int DATABASE_VERSION = 1;


    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create admin table
        String SQL_CREATE_ADMIN_TABLE = "CREATE TABLE " + DatabaseContract.AdminEntry.TABLE_NAME + "("
                + DatabaseContract.AdminEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.AdminEntry.USERNAME + " TEXT NOT NULL, "
                + DatabaseContract.AdminEntry.PASSWORD + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_ADMIN_TABLE);
        //Create doctor table
        String SQL_CREATE_DOCTORS_TABLE = "CREATE TABLE " + DatabaseContract.DoctorEntry.TABLE_NAME + "("
                + DatabaseContract.DoctorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.DoctorEntry.USERNAME + " TEXT NOT NULL, "
                + DatabaseContract.DoctorEntry.MAIL + " TEXT NOT NULL, "
                + DatabaseContract.DoctorEntry.PASSWORD + " TEXT NOT NULL, "
                + DatabaseContract.DoctorEntry.PHONE + " INTEGER);";
        db.execSQL(SQL_CREATE_DOCTORS_TABLE);
        //Create historique table
        String SQL_CREATE_HISTORIQUES_TABLE = "CREATE TABLE " + DatabaseContract.HistoriqueEntry.TABLE_NAME + "("
                + DatabaseContract.HistoriqueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.HistoriqueEntry.KETONE + " INTEGER NOT NULL, "
                + DatabaseContract.HistoriqueEntry.TEMPERATURE + " INTEGER NOT NULL, "
                + DatabaseContract.HistoriqueEntry.HUMIDITY + " INTEGER NOT NULL, "
                + DatabaseContract.HistoriqueEntry.DATE + " INTEGER NOT NULL, "
                + DatabaseContract.HistoriqueEntry.TIME + " INTEGER);";
        db.execSQL(SQL_CREATE_HISTORIQUES_TABLE);
        //Create responsable table
        String SQL_CREATE_RESPONSABLE_TABLE = "CREATE TABLE " + DatabaseContract.ResponsableEntry.TABLE_NAME + "("
                + DatabaseContract.HistoriqueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.HistoriqueEntry.KETONE + " INTEGER NOT NULL, "
                + DatabaseContract.HistoriqueEntry.TEMPERATURE + " INTEGER NOT NULL, "
                + DatabaseContract.HistoriqueEntry.HUMIDITY + " INTEGER NOT NULL, "
                + DatabaseContract.HistoriqueEntry.DATE + " DATE, "
                + DatabaseContract.HistoriqueEntry.TIME + " DATETIME);";
        db.execSQL(SQL_CREATE_RESPONSABLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
