package com.bean.coffee.duaapp.Model;

import android.content.Context;
import android.database.Cursor;

import com.bean.coffee.duaapp.Class.DatabaseHelper;
import com.bean.coffee.duaapp.Class.SqlConnection;
import com.bean.coffee.duaapp.Presenter.RamadanPresenter;
import com.bean.coffee.duaapp.View.RamadanView;

import java.util.ArrayList;
import java.util.List;

public class RamadanPresenterImpl implements RamadanPresenter{

    private RamadanView ramadanView;
    private List<String> titleList1=new ArrayList<>();
    private List<String> titleList2=new ArrayList<>();
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

        while (resultFromDatabase.moveToNext()) {
            String title=resultFromDatabase.getString(0);
            if(title!=null) {
                titleList1.add(title);
            }
        }

        ramadanView.populateListView1(titleList1);
        if(titleList1!=null) {
            ramadanView.itemClickedCallBack1();
        }
    }

    @Override
    public void getDataFromDbForListView2() {
       /* SqlConnection sqlConnection=new SqlConnection();
        DatabaseHelper myDbHelper=sqlConnection.Connect(context);*/

        Cursor resultFromDatabase=myDbHelper.getAllRamadanTitleData1();

        while (resultFromDatabase.moveToNext()) {
            String title=resultFromDatabase.getString(0);
            if(title!=null) {
                titleList2.add(title);
            }
        }

        ramadanView.populateListView2(titleList2);
        if(titleList2!=null) {
            ramadanView.itemClickedCallBack2();
        }
    }
}
