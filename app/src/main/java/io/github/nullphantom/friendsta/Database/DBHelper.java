package io.github.nullphantom.friendsta.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ilham Bintang on 27/01/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "friendsta.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_FRIEND = "friend";
    public static final String COLUMN_ID = "friendId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NO_HP= "no_hp";
    public static final String COLUMN_GAMBAR= "gambar";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_FRIEND + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_GENDER + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_NO_HP + " TEXT, " +
                    COLUMN_GAMBAR + " TEXT " +
                    ")";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FRIEND);
        db.execSQL(TABLE_CREATE);
    }
}
