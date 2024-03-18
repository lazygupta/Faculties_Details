package com.example.faculties;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FacultiesDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FACULTIES = "faculties";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_DEPARTMENT = "department";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_FACULTIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT," + KEY_CONTACT + " TEXT,"
                + KEY_DEPARTMENT + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FACULTIES);

        onCreate(db);
    }

    public void addFaculties(String name,String contact, String department){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_CONTACT, contact);
        values.put(KEY_DEPARTMENT, department);

        db.insert(TABLE_FACULTIES,null, values);
    }

    public ArrayList<FacultiesModel> getFaculties(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FACULTIES, null);

        ArrayList<FacultiesModel> arrModel = new ArrayList<>();

        while(cursor.moveToNext()){
            FacultiesModel model = new FacultiesModel();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.contact = cursor.getString(2);
            model.department = cursor.getString(3);

            arrModel.add(model);
        }

        return arrModel;
    }
}
