package com.example.dardan.elearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dardan on 11/17/2016.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_HIGHSCORES = "highscores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FRUITS = "fruits";
    public static final String COLUMN_ANIMALS = "animals";
    public static final String COLUMN_FOOD = "food";
    public static final String COLUMN_COLORS = "colors";

    private static final String DATABASE_NAME = "highscores.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table " + TABLE_HIGHSCORES
                    + "( "
                    + COLUMN_ID + " integer primary key autoincrement,"
                    + COLUMN_ANIMALS + " integer,"
                    + COLUMN_FRUITS + " integer,"
                    + COLUMN_FOOD + " integer,"
                    + COLUMN_COLORS + " integer"
                    + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ANIMALS,0);
        cv.put(COLUMN_FRUITS,0);
        cv.put(COLUMN_FOOD,0);
        cv.put(COLUMN_COLORS,0);
        db.insert(TABLE_HIGHSCORES,null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORES);
        onCreate(db);
    }
}
