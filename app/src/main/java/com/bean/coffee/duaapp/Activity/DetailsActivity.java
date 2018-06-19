package com.bean.coffee.duaapp.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bean.coffee.duaapp.Model.DetailsActivityPresenterImpl;
import com.bean.coffee.duaapp.Presenter.DetailsActivityPresenter;

import com.bean.coffee.duaapp.R;
import com.bean.coffee.duaapp.View.DetailsActivityView;

public class DetailsActivity extends AppCompatActivity implements DetailsActivityView{

    //vars
    private DetailsActivityPresenter detailsActivityPresenter;
    private String title,tableName;

    //widgets
    private TextView titleTE,detailsTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleTE=findViewById(R.id.titleTE);
        detailsTE=findViewById(R.id.detailsTE);

        detailsActivityPresenter=new DetailsActivityPresenterImpl(this);

        title=getIntent().getStringExtra("TITLE_NAME");
        tableName=getIntent().getStringExtra("TABLE_NAME");

        if(title!=null) {
            detailsActivityPresenter.getAndPopulateData(this,title,tableName);
        }
    }


    @Override
    public void setTitle(String title) {
        titleTE.setText(title);
    }

    @Override
    public void setDetail(String detail) {
        detailsTE.setText(detail);
    }
}
