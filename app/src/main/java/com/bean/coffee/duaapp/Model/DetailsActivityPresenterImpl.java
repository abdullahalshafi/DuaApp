package com.bean.coffee.duaapp.Model;

import android.content.Context;
import android.database.Cursor;

import com.bean.coffee.duaapp.Class.DatabaseHelper;
import com.bean.coffee.duaapp.Class.SqlConnection;
import com.bean.coffee.duaapp.Presenter.DetailsActivityPresenter;
import com.bean.coffee.duaapp.View.DetailsActivityView;

public class DetailsActivityPresenterImpl implements DetailsActivityPresenter {

    DetailsActivityView detailsActivityView;

    public DetailsActivityPresenterImpl(DetailsActivityView detailsActivityView){
        this.detailsActivityView=detailsActivityView;
    }


    @Override
    public void getAndPopulateData(Context context,String titleName,String tableName) {

        SqlConnection sqlConnection=new SqlConnection();
        DatabaseHelper myDbHelper=sqlConnection.Connect(context);

        Cursor resultFromDatabase=myDbHelper.getAllDetailsData(titleName,tableName);

        while (resultFromDatabase.moveToNext()) {
            String title=resultFromDatabase.getString(0);
            String details=resultFromDatabase.getString(1);
            if(title!=null && details!=null) {
                detailsActivityView.setTitle(title);
                detailsActivityView.setDetail(details);
            }
        }
    }
}
