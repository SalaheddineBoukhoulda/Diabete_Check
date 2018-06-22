package com.mr_rude.healthassistance.Data;

import android.provider.BaseColumns;

/**
 * Created by Mr_Rude on 16/06/2018.
 */

public final class DatabaseContract {

    public static final class AdminEntry implements BaseColumns{
        public final static String TABLE_NAME = "admin";

        public final static String _ID = BaseColumns._ID;
        public final static String USERNAME = "username";
        public final static String PASSWORD = "password";
    }

    public static final class DoctorEntry implements BaseColumns{
        public final static String TABLE_NAME = "doctor";

        public final static String _ID = BaseColumns._ID;
        public final static String USERNAME = "username";
        public final static String MAIL = "mail";
        public final static String PASSWORD = "pass";
        public final static String PHONE = "phone";

    }


    public static final class HistoriqueEntry implements BaseColumns{
        public final static String TABLE_NAME = "historique";

        public final static String _ID = BaseColumns._ID;
        public final static String ID_OBJ = "idobj";
        public final static String KETONE = "ketone";
        public final static String TEMPERATURE = "temperature";
        public final static String HUMIDITY = "humidity";
        public final static String DATE = "date";
        public final static String TIME = "time";
    }


    public static final class ResponsableEntry implements BaseColumns{
        public final static String TABLE_NAME = "responsable";

        public final static String _ID = BaseColumns._ID;
        public final static String USERNAME = "username";
        public final static String PASSWORD = "password";
        public final static String NUMBER = "number";
        public final static String IDOBJ = "idobj";
        public final static String ADRESS = "adress";
        public final static String DOCNAME = "docname";
    }
}
