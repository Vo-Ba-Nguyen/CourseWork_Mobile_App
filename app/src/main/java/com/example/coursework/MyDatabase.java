package com.example.coursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "TripDatabase.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_trip_database";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "trip_name";
    private static final String COLUMN_DESTINATION = "trip_destination";
    private static final String COLUMN_DATE = "date_of_trip";
    private static final String COLUMN_ASSESSMENT = "trip_assessment";
    private static final String COLUMN_DESCRIPTION = "description";

    MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        "  (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESTINATION + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_ASSESSMENT + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addTrip(String name, String destination, String date,String trip_assessment, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESTINATION,destination);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_ASSESSMENT,trip_assessment);
        cv.put(COLUMN_DESCRIPTION,description);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Add Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null){
           cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id,String name,String destination, String date, String trip_assessment, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DESTINATION,destination);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_ASSESSMENT,trip_assessment);
        cv.put(COLUMN_DESCRIPTION,description);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
       long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
       if (result == -1){
           Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show();
       }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    ArrayList<TripModel> showAll(){
        ArrayList<TripModel> tripModelArrayList = null;

        tripModelArrayList = new ArrayList<TripModel>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){
                TripModel model = new TripModel();
                model.setId(cursor.getInt(0));
                model.setTripName(cursor.getString(1));
                model.setTripDestination(cursor.getString(2));
                model.setTripDate(cursor.getString(3));
                model.setTripAssessment(cursor.getString(4));
                model.setTripDescription(cursor.getString(5));
                tripModelArrayList.add(model);
            }

        } catch (Exception e ){
            tripModelArrayList = null;
        }
        return tripModelArrayList;
    }
}
