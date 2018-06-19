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
    private List<String> titleList1=new ArrayList<>();
    private List<String> titleList2=new ArrayList<>();
    private List<String> titleList3=new ArrayList<>();
    private DatabaseHelper myDbHelper;


    public DailyDuaPresenterImpl(DailyDuaView dailyDuaView,Context context){

        this.dailyDuaView = dailyDuaView;
        SqlConnection sqlConnection=new SqlConnection();
        myDbHelper=sqlConnection.Connect(context);
    }

    @Override
    public void getDataFromDbForListView1() {
        Cursor resultFromDatabase=myDbHelper.getAllTitleData();

        while (resultFromDatabase.moveToNext()) {
            String title=resultFromDatabase.getString(0);
            if(title!=null) {
                titleList1.add(title);
            }
        }

        dailyDuaView.populateListView1(titleList1);
        if(titleList1!=null) {
            dailyDuaView.itemClickedCallBack1();
        }
    }

    @Override
    public void getDataFromDbForListView2() {

        Cursor resultFromDatabase=myDbHelper.getAllTitleData1();

        while (resultFromDatabase.moveToNext()) {
            String title=resultFromDatabase.getString(0);
            if(title!=null) {
                titleList2.add(title);
            }
        }

        dailyDuaView.populateListView2(titleList2);
        if(titleList2!=null) {
            dailyDuaView.itemClickedCallBack2();
        }
    }

    @Override
    public void getDataFromDbForListView3() {

        Cursor resultFromDatabase=myDbHelper.getAllTitleData2();

        while (resultFromDatabase.moveToNext()) {
            String title=resultFromDatabase.getString(0);
            if(title!=null) {
                titleList3.add(title);
            }
        }

        dailyDuaView.populateListView3(titleList3);
        if(titleList3!=null) {
            dailyDuaView.itemClickedCallBack3();
        }
    }
}
