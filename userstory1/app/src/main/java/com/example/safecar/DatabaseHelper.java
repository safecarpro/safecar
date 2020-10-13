package com.example.safecar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "safe.db";
    public static final String TABLE_NAME = "registrationtable";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "address";
    public static final String COL_4 = "gender";
    public static final String COL_5 = "email";
    public static final String COL_6 = "phnno";
    public static final String COL_7 = "password";
    public static final String COL_8 = "location";
    public static final String TABLE_DATE = "datetable";
    public static final String COL_id = "D_id";
    public static final String COL_pick = "datepick";
    public static final String COL_drop = "datedrop";
    //car details
    public static final String TABLE_CAR = "cartable";
    public static final String COL_cid = "cid";
    public static final String COL_cbrand = "brand";
    public static final String COL_cmodel = "model";
    public static final String COL_camount = "amount";
    public static final String COL_cagency = "agency";
    public static final String COL_ckms = "kms";
    public static final String COL_cphone = "phone";
    public static final String COL_cloc = "loation";
    public static final String COL_cemail = "email";
    public static final String COL_iv = "iv";
    //Driver details
    public static final String TABLE_DRIVER = "drivertable";
    public static final String COL_did = "did";
    public static final String COL_dname = "name";
    public static final String COL_daddress = "address";
    public static final String COL_dage = "age";
    public static final String COL_dgender = "gender";
    public static final String COL_dcharge = "charge";
    public static final String COL_dbadges = "badges";
    public static final String COL_dlocation = "location";
    public static final String COL_dyoe = "yearofexperience";
    public static final String COL_dphno = "phno";
    public static final String COL_demail = "email";
    public static final String COL_v = "v";
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

    //CREATE TABLE DATE
    private String CREATE_DATE_TABLE = "CREATE TABLE " + TABLE_DATE + "("
            + COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_pick + " TEXT,"
            + COL_drop + " TEXT"
            + ")";
    // CREATE CAR TABLE

    private String CREATE_CAR_TABLE = "CREATE TABLE " + TABLE_CAR + "("
            + COL_cid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_cbrand + " TEXT,"
            + COL_cmodel + " TEXT,"
            + COL_camount + " TEXT,"
            + COL_cagency + " TEXT,"
            + COL_ckms + " TEXT,"
            + COL_cphone + " TEXT,"
            + COL_cloc + " TEXT,"
            + COL_cemail + " TEXT,"
            + COL_iv + " BLOB" + ")";
// CREATE DRIVER TABLE

    private String CREATE_DRIVER_TABLE = "CREATE TABLE " + TABLE_DRIVER + "("
            +COL_did+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_dname + " TEXT,"
            + COL_daddress+ " TEXT,"
            + COL_dage + " TEXT,"
            + COL_dgender + " TEXT,"
            + COL_dcharge + " TEXT,"
            + COL_dbadges + " TEXT,"
            + COL_dlocation + " TEXT,"
            + COL_dyoe + " TEXT,"
            + COL_dphno + " TEXT,"
            + COL_demail + " TEXT,"
            + COL_v + " BLOB" + ")";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 3);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_DATE_TABLE);
        db.execSQL(CREATE_CAR_TABLE);
        db.execSQL(CREATE_DRIVER_TABLE);
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

        //inserting data to date table

    public boolean insertdatedata(String datepick, String datedrop) {

        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_pick, datepick);
        contentValues.put(COL_drop, datedrop);

        long result = db2.insert(TABLE_DATE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    //INSERT CAR DATA

    public boolean insertcardata(String cbrand, String cmodel, String camount, String cagency, String ckms, String cphone, String cloc, String cemail, byte[] img) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_cbrand, cbrand);
        contentValues.put(COL_cmodel, cmodel);
        contentValues.put(COL_camount, camount);
        contentValues.put(COL_cagency, cagency);
        contentValues.put(COL_ckms, ckms);
        contentValues.put(COL_cphone, cphone);
        contentValues.put(COL_cloc, cloc);
        contentValues.put(COL_cemail, cemail);
        contentValues.put(COL_iv, img);
        long result = db.insert(TABLE_CAR, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public byte[] carImage(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] col=new String[]{COL_iv};  // your column which data u want to retrive if id is same
        Cursor c=db.query(TABLE_CAR, col, COL_cid+"="+_id,null, null, null, null);
        if (c.moveToFirst()) {
            byte[] blob = c.getBlob(c.getColumnIndex(COL_iv));
            c.close();
            return blob;
        }
        c.close();
        return null;
    }

    public byte[] driverImage(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] col=new String[]{COL_v};  // your column which data u want to retrive if id is same
        Cursor c=db.query(TABLE_DRIVER, col, COL_did+"="+_id,null, null, null, null);
        if (c.moveToFirst()) {
            byte[] blob = c.getBlob(c.getColumnIndex(COL_v));
            c.close();
            return blob;
        }
        c.close();
        return null;
    }

    public boolean insertdriverdata(String dname, String daddress, String dage, String dgender, String dcharge, String dbadge,  String dlocation,String dyoe,String dphno,String demail, byte[] img) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_dname, dname);
        contentValues.put(COL_daddress, daddress);
        contentValues.put(COL_dage, dage);
        contentValues.put(COL_dgender, dgender);
        contentValues.put(COL_dcharge, dcharge);
        contentValues.put(COL_dbadges, dbadge);
        contentValues.put(COL_dlocation, dlocation);
        contentValues.put(COL_dyoe, dyoe);
        contentValues.put(COL_dphno, dphno);
        contentValues.put(COL_demail, demail);
        contentValues.put(COL_v, img);
        long result = db.insert(TABLE_DRIVER, null, contentValues);
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
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


}

