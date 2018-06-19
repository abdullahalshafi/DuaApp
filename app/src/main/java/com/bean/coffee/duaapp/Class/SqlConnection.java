package com.bean.coffee.duaapp.Class;

import android.content.Context;
import android.database.SQLException;

import java.io.IOException;



public class SqlConnection {

    public DatabaseHelper Connect(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        try {
            myDbHelper.createDatabase();
        } catch (IOException i) {
            throw new Error("unable to create database");
        }

        try {
            myDbHelper.openDatabase();
        } catch (SQLException sql) {
            throw sql;
        }
        return myDbHelper;
    }

}
