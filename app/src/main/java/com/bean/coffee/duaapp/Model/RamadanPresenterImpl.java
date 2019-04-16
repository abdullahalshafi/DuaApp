package com.bean.coffee.duaapp.Model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.bean.coffee.duaapp.Class.DatabaseHelper;
import com.bean.coffee.duaapp.Class.SqlConnection;
import com.bean.coffee.duaapp.Presenter.RamadanPresenter;
import com.bean.coffee.duaapp.View.RamadanView;

import java.util.ArrayList;
import java.util.List;

public class RamadanPresenterImpl implements RamadanPresenter{

    private RamadanView ramadanView;
    private List<String> ramadanDua=new ArrayList<>();
    private DatabaseHelper myDbHelper;

    public RamadanPresenterImpl(RamadanView ramadanView,Context context)
    {

        this.ramadanView=ramadanView;
        SqlConnection sqlConnection=new SqlConnection();
        myDbHelper=sqlConnection.Connect(context);

    }

    @Override
    public void getDataFromDbForListView1() {

      /*  SqlConnection sqlConnection=new SqlConnection();
        DatabaseHelper myDbHelper=sqlConnection.Connect(context);*/

        Cursor resultFromDatabase=myDbHelper.getAllRamadanTitleData();
        ramadanDua.clear();
        while (resultFromDatabase.moveToNext()) {
            String title=resultFromDatabase.getString(0);
            if(title!=null) {
                ramadanDua.add(title);
            }
        }

        ramadanView.populateListView1(ramadanDua);
    }

    @Override
    public void getDataFromDbForListView2() {
       /* SqlConnection sqlConnection=new SqlConnection();
        DatabaseHelper myDbHelper=sqlConnection.Connect(context);*/

        Cursor resultFromDatabase=myDbHelper.getAllRamadanTitleData1();
        ramadanDua.clear();
        while (resultFromDatabase.moveToNext()) {
            String title=resultFromDatabase.getString(0);
            if(title!=null) {
                ramadanDua.add(title);
            }
        }

        ramadanView.populateListView2(ramadanDua);
    }
}
