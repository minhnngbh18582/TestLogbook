package com.example.testlogbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_LOGBOOK";
    private static final String TABLE_NAME = "Images";

    public static final String IMG_ID = "image_id";
    public static final String IMG_URL = "image_url";

    private SQLiteDatabase database;

    private static final String TABLE_NAME_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT)",
            TABLE_NAME, IMG_ID, IMG_URL);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_NAME_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        Log.v(this.getClass().getName(), TABLE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(db);
    }

    public long insertImage(String image_url) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(IMG_URL, image_url);
        return database.insertOrThrow(TABLE_NAME, null, rowValues);
    }

    public ArrayList<Image> getImages(){
        Cursor cursor = database.query(TABLE_NAME, new String[] {IMG_ID, IMG_URL},
                null, null, null, null, "image_id");

        ArrayList<Image> results = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String imageURL = cursor.getString(1);

            Image image = new Image();
            image.setImageId(id);
            image.setImageURL(imageURL);

            results.add(image);

            cursor.moveToNext();
        }
        return  results;
    }
}
