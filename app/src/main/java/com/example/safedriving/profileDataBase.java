package com.example.safedriving;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class profileDataBase extends SQLiteOpenHelper {
    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "ProfileDatabase";
    private static final String DATABASE_TABLE = "profilesdb";

    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String KEY_TASK_ID = "_id";
    private static final String KEY_ACCOUNTID = "accountid";     // beer/wine/spirit
    private static final String KEY_GENDER = "gender";   // company/brand
    private static final String KEY_WEIGHT = "weight";    // alcohol % by volume

    private int users;

    public profileDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_TASK_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ACCOUNTID + " TEXT, " + KEY_GENDER + " TEXT, "
                + KEY_WEIGHT + " DOUBLE" + ")";

        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // DROP OLDER TABLE IF EXISTS
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // CREATE TABLE AGAIN
        onCreate(database);
    }


    //********** DATABASE OPERATIONS:  ADD, EDIT, DELETE
    public void addUser(User d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ACCOUNTID, d.getToken());

        values.put(KEY_GENDER, d.getGender());

        values.put(KEY_WEIGHT, d.getWeight());

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values);
        users++;

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public void clearAll(List<User> list) {
        //GET ALL THE LIST TASK ITEMS AND CLEAR THEM
        list.clear();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, new String[]{});
        db.close();
    }

    public void updateUser(User d) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNTID, d.getToken());

        values.put(KEY_GENDER, d.getGender());

        values.put(KEY_WEIGHT, d.getWeight());

        db.update(DATABASE_TABLE, values,
                KEY_TASK_ID + " = ?", new String[]{String.valueOf(d.getId())});
        db.close();
    }

    // TESTING DATABASE POPULATION
    public List<User> getAllTasks() {

        //GET ALL THE TASK ITEMS ON THE LIST
        List<User> todoList = new ArrayList<User>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // LOOP THROUGH THE TODO TASKS
        if (cursor.moveToFirst()) {
            do {
                User task = new User();
                task.setToken(cursor.getString(cursor.getColumnIndexOrThrow(KEY_ACCOUNTID)));
                task.setGender(cursor.getString(cursor.getColumnIndexOrThrow(KEY_GENDER)));
                task.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow(KEY_WEIGHT)));
                todoList.add(task);
            } while (cursor.moveToNext());
        }

        // RETURN THE LIST OF TASKS FROM THE TABLE
//        for (User d : todoList) {
//            Log.i("User FROM GETALL:  ", d.str());
//        }
        return todoList;
    }
    // TILL HERE

    public User getUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
        String selectQuery  = "SELECT * FROM profilesdb WHERE accountid LIKE " + "'" + id + "'";
        Cursor c = db.rawQuery(selectQuery, null);

        //Log.i("HOW MANY U IN QUERY", String.valueOf(c.getCount()));

        User d = new User();
        if(c != null && c.moveToFirst()){
            d.setToken(c.getString(c.getColumnIndexOrThrow(KEY_ACCOUNTID)));
            d.setGender(c.getString(c.getColumnIndexOrThrow(KEY_GENDER)));
            d.setWeight(c.getDouble(c.getColumnIndexOrThrow(KEY_WEIGHT)));
            c.close();
        }
        else {
            return null;
           // Log.i("CURSOR CHECKING:   ", String.valueOf(c == null));
           // Log.i("CURSOR CHECKING:   ", String.valueOf(c.moveToFirst()));
        }

        //Log.i("USER FROM GET:  ", d.str());
        return d;
    }
}

