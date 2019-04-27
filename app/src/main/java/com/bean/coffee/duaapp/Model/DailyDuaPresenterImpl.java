package com.bean.coffee.duaapp.Model;

import android.content.Context;
import android.database.Cursor;

import com.bean.coffee.duaapp.Class.DatabaseHelper;
import com.bean.coffee.duaapp.Class.SqlConnection;
import com.bean.coffee.duaapp.Presenter.DailyDuaPresenter;
import com.bean.coffee.duaapp.View.DailyDuaView;

import java.util.ArrayList;
import java.util.List;

public class DailyDuaPresenterImpl implements DailyDuaPresenter {

    private DailyDuaView dailyDuaView;
    private List<String> dailyDuas = new ArrayList<>();
    private DatabaseHelper myDbHelper;


    public DailyDuaPresenterImpl(DailyDuaView dailyDuaView, Context context) {

        this.dailyDuaView = dailyDuaView;
        SqlConnection sqlConnection = new SqlConnection();
        myDbHelper = sqlConnection.Connect(context);
    }

    @Override
    public void getDataFromDbForListView1() {
        Cursor resultFromDatabase = myDbHelper.getAllTitleData();
        dailyDuas.clear();
        while (resultFromDatabase.moveToNext()) {
            String title = resultFromDatabase.getString(0);
            if (title != null) {
                dailyDuas.add(title);
            }
        }
        dailyDuaView.populateListView1(dailyDuas);
    }

    @Override
    public void getDataFromDbForListView2() {

        Cursor resultFromDatabase = myDbHelper.getAllTitleData1();
        dailyDuas.clear();
        while (resultFromDatabase.moveToNext()) {
            String title = resultFromDatabase.getString(0);
            if (title != null) {
                dailyDuas.add(title);
            }
        }

        dailyDuaView.populateListView2(dailyDuas);
    }

    @Override
    public void getDataFromDbForListView3() {

        Cursor resultFromDatabase = myDbHelper.getAllTitleData2();
        dailyDuas.clear();
        while (resultFromDatabase.moveToNext()) {
            String title = resultFromDatabase.getString(0);
            if (title != null) {
                dailyDuas.add(title);
            }
        }
        dailyDuaView.populateListView3(dailyDuas);

    }
}
