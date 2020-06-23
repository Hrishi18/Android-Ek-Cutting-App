package com.hrishikeshgawas.ekcutting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavDB extends SQLiteOpenHelper {
    public static int DB_VERSION = 17;
    public static String DATABASE_NAME = "ShopDB";
    public static String TABLE_NAME = "favouriteTable";
    public static String KEY_ID = "id";
    public static String ITEM_NAME = "itemName";
    public static String ITEM_ADDRESS = "itemAddress";
    public static String ITEM_DESCRIPTION = "itemDescription";
    public static String ITEM_COST = "itemCost";
    public static String FAVOURITE_STATUS = "fStatus";
    public static String ITEM_LINK = "itemLink";

    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " TEXT," + ITEM_NAME + " TEXT," + ITEM_ADDRESS +" TEXT," + ITEM_DESCRIPTION + " TEXT," + ITEM_COST + " TEXT," +FAVOURITE_STATUS + " TEXT," + ITEM_LINK + " TEXT)";

    public FavDB(Context context ){super(context,DATABASE_NAME,null,DB_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // create empty table
    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // enter your value
        for (int x = 1; x < 11; x++) {
            cv.put(KEY_ID, x);
            cv.put(FAVOURITE_STATUS, "0");

            db.insert(TABLE_NAME,null, cv);
        }
    }

    // insert data into database
    public void insertIntoTheDatabase(String id, String item_name, String item_address, String item_description, String item_cost,  String fav_status, String item_link) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, id);
        cv.put(ITEM_NAME, item_name);
        cv.put(ITEM_ADDRESS, item_address);
        cv.put(ITEM_DESCRIPTION, item_description);
        cv.put(ITEM_COST, item_cost);
        cv.put(FAVOURITE_STATUS, fav_status);
        cv.put(ITEM_LINK, item_link);
         db.insert(TABLE_NAME,null, cv);
        Log.d("FavDB Status", item_name + ", favstatus - "+fav_status+" - . " + cv);
    }

    // read all data
    public Cursor read_all_data(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID+"="+id+"";
        return db.rawQuery(sql,null,null);
    }

    // remove line from database
    public void remove_fav(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ FAVOURITE_STATUS+" ='0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());

    }

    // select all favorite list

    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+FAVOURITE_STATUS+" ='1'";
        return db.rawQuery(sql,null,null);
    }





}
