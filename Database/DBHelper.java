package com.example.teamproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    static DBHelper helper = null;

    public static DBHelper getInstance(Context context){
        if (helper == null){
            helper = new DBHelper(context);
        }
        return helper;
    }

    private DBHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS COURSE (\n" +
                "    Cnumber INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    Cname VARCHAR(20),\n" +
                "    Credit INTEGER\n" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS TAKE_COURSE (\n" +
                "    Cnum INTEGER PRIMARY KEY,\n" +
                "    Grade VARCHAR(2) NOT NULL,\n" +
                "    FOREIGN KEY(Cnum) REFERENCES COURSE (Cumber)\n" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS COURSE_CATEGORY (\n" +
                "    Id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    Major TEXT NOT NULL,\n" +
                "    Cno INTEGER NOT NULL,\n" +
                "    Category TEXT NOT NULL,\n" +
                "    FOREIGN KEY(Cno) REFERENCES COURSE (Cumber)\n" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS ATTACHMENT (\n" +
                "    ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    Title TEXT,\n" +
                "    Contents TEXT,\n" +
                "    File TEXT,\n" +
                "    Remark TEXT\n" +
                ");");

        LectureData data = new LectureData();
        ArrayList<String> row = data.getCourseData();
        for (String each : row ){
            db.execSQL(each);
        }
        row = data.getCategoryData();
        for (String each : row ){
            db.execSQL(each);
        }
    }

    public void insertTakeCourse(SQLiteDatabase db, int cnum, String grade){
        db.execSQL(String.format("INSERT INTO TAKE_COURSE (Cnum, Grade) VALUES (%d, '%s');", cnum, grade));
    }
    public void deleteTakeCourse(SQLiteDatabase db, int cnum){
        db.execSQL(String.format("DELETE FROM TAKE_COURSE WHERE Cnum = %d;", cnum));
    }
    public void updateTakeCourse(SQLiteDatabase db, String grade, String where){
        db.execSQL(String.format("UPDATE TAKE_COURSE SET Grade = '%s' WHERE %s;", grade, where));
    }

    public void insertCourseCategory(SQLiteDatabase db, String major, int cno, String category){
        db.execSQL(String.format("INSERT INTO COURSE_CATEGORY (Major, Cno, Category) VALUES ('%s', %d, '%s');", major, cno, category));
    }
    public void updateCourseCategory(SQLiteDatabase db, String category, String where){
        db.execSQL(String.format("UPDATE COURSE_CATEGORY SET Category = '%s' WHERE %s;", category, where));
    }

    public void insertAttachment(SQLiteDatabase db, String title, String contents, String file, String remark){
        db.execSQL(String.format("INSERT INTO ATTACHMENT (Title, Contents, File, Remark) VALUES ('%s', '%s', '%s', '%s');", title, contents, file, remark));
    }
    public void deleteAttachment(SQLiteDatabase db, int id){
        db.execSQL(String.format("DELETE FROM ATTACHMENT WHERE Id = %d;", id));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
