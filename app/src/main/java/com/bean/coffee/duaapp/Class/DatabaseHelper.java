package com.bean.coffee.duaapp.Class;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "dua.db";
    private final static int DB_VERSION = 2;
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

    }

    public void createDatabase() throws IOException {

        deleteOldDatabase();
        boolean dbExist = checkDataBase();
        SQLiteDatabase db_Read = null;

        if (dbExist) {

        } else {
            db_Read = this.getReadableDatabase();
            db_Read.close();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error(e);
            }
        }
    }

    private void deleteOldDatabase() {

        //myContext.deleteDatabase("");
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        } catch (SQLException e) {

        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }


    private void copyDataBase() throws IOException {

        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0) {

            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLException {

        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //For Database Search

    public Cursor getAllTitleData() {

        String query = "SELECT title FROM dua WHERE rownum <=5";
        Cursor res = myDataBase.rawQuery(query, null);
        return res;
    }

    public Cursor getAllTitleData1() {

        String query = "SELECT title FROM dua WHERE rownum >=6 and rownum <=10";
        Cursor res = myDataBase.rawQuery(query, null);
        return res;
    }

    public Cursor getAllTitleData2() {

        String query = "SELECT title FROM dua WHERE rownum >=11 and rownum <=15";
        Cursor res = myDataBase.rawQuery(query, null);
        return res;
    }

    public Cursor getAllRamadanTitleData() {

        String query = "SELECT title FROM romjan WHERE rownum <=5";
        Cursor res = myDataBase.rawQuery(query, null);
        return res;
    }

    public Cursor getAllRamadanTitleData1() {

        String query = "SELECT title FROM romjan WHERE rownum >=6";
        Cursor res = myDataBase.rawQuery(query, null);
        return res;
    }

    public Cursor getAllDetailsData(String titleName, String tableName) {

        String query = "SELECT * FROM " + tableName + " where title = ?";
        Cursor res = myDataBase.rawQuery(query, new String[]{titleName});
        return res;
    }

}
