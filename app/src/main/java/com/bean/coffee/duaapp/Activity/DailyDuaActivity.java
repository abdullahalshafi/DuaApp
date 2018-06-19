package com.bean.coffee.duaapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bean.coffee.duaapp.Class.ListViewAdapter;
import com.bean.coffee.duaapp.Model.DailyDuaPresenterImpl;
import com.bean.coffee.duaapp.Presenter.DailyDuaPresenter;
import com.bean.coffee.duaapp.R;
import com.bean.coffee.duaapp.View.DailyDuaView;

import java.util.ArrayList;
import java.util.List;

public class DailyDuaActivity extends AppCompatActivity implements DailyDuaView {

    //vars
    private List<String> titleList1=new ArrayList<>();
    private List<String> titleList2=new ArrayList<>();
    private List<String> titleList3=new ArrayList<>();

    ArrayAdapter<String> titleArrayAdapter1;
    ArrayAdapter<String> titleArrayAdapter2;
    ArrayAdapter<String> titleArrayAdapter3;

    DailyDuaPresenter dailyDuaPresenter;

    //widgets
    private ListView titleListView1,titleListView2,titleListView3;
    private Button nextBTN,prevBTN,backBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_dua);

        titleListView1=findViewById(R.id.titleListView1);
        titleListView2=findViewById(R.id.titleListView2);
        titleListView3=findViewById(R.id.titleListView3);
        nextBTN=findViewById(R.id.nextBTN);
        prevBTN=findViewById(R.id.prevBTN);
        backBTN=findViewById(R.id.backBTN);

        dailyDuaPresenter=new DailyDuaPresenterImpl(DailyDuaActivity.this,this);
        dailyDuaPresenter.getDataFromDbForListView1();
        dailyDuaPresenter.getDataFromDbForListView2();
        dailyDuaPresenter.getDataFromDbForListView3();


        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleListView1.getVisibility()==View.VISIBLE){

                    titleListView1.setVisibility(View.GONE);
                    titleListView2.setVisibility(View.VISIBLE);


                }
                else if(titleListView2.getVisibility()==View.VISIBLE) {
                    titleListView2.setVisibility(View.GONE);
                    titleListView3.setVisibility(View.VISIBLE);
                }
            }
        });

        prevBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleListView3.getVisibility()==View.VISIBLE) {
                    titleListView3.setVisibility(View.GONE);
                    titleListView2.setVisibility(View.VISIBLE);
                }
                else if(titleListView2.getVisibility()==View.VISIBLE) {
                    titleListView2.setVisibility(View.GONE);
                    titleListView1.setVisibility(View.VISIBLE);
                }
            }
        });

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    @Override
    public void populateListView1(List<String> titleList) {

        if(titleList!=null) {
            this.titleList1=titleList;
            titleArrayAdapter1 =new ListViewAdapter(this,titleList1);
            titleListView1.setAdapter(titleArrayAdapter1);

        }else{
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void populateListView2(List<String> titleList) {

        if(titleList!=null) {
             this.titleList2=titleList;
            titleArrayAdapter2 =new ListViewAdapter(this,titleList2);
            titleListView2.setAdapter(titleArrayAdapter2);

        }else{
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void populateListView3(List<String> titleList) {

        if(titleList!=null) {
            this.titleList3=titleList;
            titleArrayAdapter3 =new ListViewAdapter(this,titleList3);
            titleListView3.setAdapter(titleArrayAdapter3);


        }else{
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void itemClickedCallBack1() {

        titleListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(titleList1!=null) {
                    String title = titleList1.get(position);
                    fireIntent(title);
                }else{
                    Toast.makeText(DailyDuaActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void itemClickedCallBack2() {
        titleListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(titleList2!=null) {
                    String title = titleList2.get(position);
                    fireIntent(title);
                }else{
                    Toast.makeText(DailyDuaActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void itemClickedCallBack3() {

        titleListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(titleList3!=null) {
                    String title = titleList3.get(position);
                    fireIntent(title);
                }else{
                    Toast.makeText(DailyDuaActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fireIntent(String title) {
        Intent intent = new Intent(DailyDuaActivity.this, DetailsActivity.class);
        intent.putExtra("TITLE_NAME", title);
        intent.putExtra("TABLE_NAME","dua");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
