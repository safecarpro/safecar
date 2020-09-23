package com.example.safecar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "registration.db";
    public static final String TABLE_NAME = "registrationtable";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "address";
    public static final String COL_4 = "gender";
    public static final String COL_5 = "email";
    public static final String COL_6 = "phnno";
    public static final String COL_7 = "password";
    public static final String COL_8 = "location";

    // create  user table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_2 + " TEXT,"
            + COL_3 + " TEXT,"
            + COL_4 + " TEXT,"
            + COL_5 + " TEXT,"
            + COL_6 + " TEXT,"
            + COL_7 + " TEXT,"
            + COL_8 + " TEXT" + ")";


    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertdata(String username, String address, String gender, String email, String phnno, String password, String location) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, gender);
        contentValues.put(COL_5, email);
        contentValues.put(COL_6, phnno);
        contentValues.put(COL_7, password);
        contentValues.put(COL_8, location);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }



    public UserModel Authenticate(UserModel userModel) {

        // array of columns to fetch
        String[] columns = {
                COL_1,
                COL_2,
                COL_3,
                COL_4,
                COL_5,
                COL_6,
                COL_7,
                COL_8
        };
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                null,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            do {

                UserModel user1 = new UserModel(cursor.getString(cursor.getColumnIndex(this.COL_1)),
                        cursor.getString(cursor.getColumnIndex(this.COL_2)),
                        cursor.getString(cursor.getColumnIndex(this.COL_3)),
                        cursor.getString(cursor.getColumnIndex(this.COL_4)),
                        cursor.getString(cursor.getColumnIndex(this.COL_5)),
                        cursor.getString(cursor.getColumnIndex(this.COL_6)),
                        cursor.getString(cursor.getColumnIndex(this.COL_7)),
                        cursor.getString(cursor.getColumnIndex(this.COL_8)));

                if (userModel.password.equalsIgnoreCase(user1.password) && userModel.username.equals(user1.username)) {
                    return user1;

                }
            } while (cursor.moveToNext());

        }
        cursor.close();
        return null;
    }


    public Cursor singleUser(String _id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_1+" = '"+_id+"'",null);

        return  cursor;
    }



}

