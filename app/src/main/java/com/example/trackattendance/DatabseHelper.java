package com.example.trackattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabseHelper extends SQLiteOpenHelper {
    private static DatabseHelper instance = null;
    private Context context;

    public static DatabseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private static final String TAG = "DatabseHelper";
    private static final String TABLE_NAME = "subjects";
    private static final String COL1 = "subject_name";
    private static final String COL2= "classes_attended";
    private static final String COL3 = "total_classes";
    private static final String COL4 = "id";

    public DatabseHelper(Context context){
        super(context, TABLE_NAME, null,1);
        this.context = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " TEXT PRIMARY KEY, " + COL2 + " INT, " + COL3 + " INT)";
        db.execSQL(createTable);
    }

    public boolean addData(String subjectName, int attended, int total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, subjectName);
        contentValues.put(COL2, attended);
        contentValues.put(COL3, total);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public void incrementAttended(String subjectName){
        SQLiteDatabase db = this.getWritableDatabase();
        final String str1  = "UPDATE subjects SET classes_attended = classes_attended + 1 WHERE subject_name= '"+ subjectName +"'";
        db.execSQL(str1);
        final String str2  = "UPDATE subjects SET total_classes = total_classes + 1 WHERE subject_name= '"+ subjectName +"'";
        db.execSQL(str2);
    }

    public void incrementTotal(String subjectName){
        SQLiteDatabase db = this.getWritableDatabase();
        final String str  = "UPDATE subjects SET total_classes = total_classes + 1 WHERE subject_name= '"+ subjectName +"'";
        db.execSQL(str);
    }
    public void deleteSubject(String subjectName){
        SQLiteDatabase db = this.getWritableDatabase();
        final String str  = "DELETE FROM subjects WHERE subject_name= '"+ subjectName +"'";
        db.execSQL(str);

    }

    public void resetSubjectAttendance(String subjectName){
        SQLiteDatabase db = this.getWritableDatabase();
        final String str  = "UPDATE subjects SET total_classes =0, classes_attended = 0 WHERE subject_name= '"+ subjectName +"'";
        db.execSQL(str);

    }

//    public Cursor getItemID(String subjectName){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = " + "'" +subjectName + "'";
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }
//     public  boolean tableExists()
//    {
//        SQLiteDatabase db = getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", TABLE_NAME});
//        if (!cursor.moveToFirst())
//        {
//            cursor.close();
//            return false;
//        }
//        int count = cursor.getInt(0);
//        cursor.close();
//        return count > 0;
//    }
}