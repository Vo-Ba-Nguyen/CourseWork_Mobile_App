package com.example.coursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ExpenseDatabase extends SQLiteOpenHelper {

    private Context context;

    private static final String  DATABASE_NAME = "ExpenseDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_expense_database";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TYPE = "type_expense";
    private static final String COLUMN_AMOUNT = "amount_expense";
    private static final String COLUMN_DATE = "date_of_expense";

    public ExpenseDatabase(@Nullable Context context) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query2 = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_DATE + " TEXT);";
        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }

    void addExpense(String type, String amount, String date_expense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_DATE, date_expense);
        long result = db.insert(TABLE_NAME, null,cv);
        if (result == -1){
            Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Add Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllDataExpense(){
        String query2 = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query2, null);
        }
        return cursor;
    }
}
