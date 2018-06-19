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
import com.bean.coffee.duaapp.Model.RamadanPresenterImpl;
import com.bean.coffee.duaapp.Presenter.RamadanPresenter;
import com.bean.coffee.duaapp.R;
import com.bean.coffee.duaapp.View.RamadanView;

import java.util.ArrayList;
import java.util.List;

public class RamadanActivity extends AppCompatActivity implements RamadanView {

    //vars
    private List<String> titleList1=new ArrayList<>();
    private List<String> titleList2=new ArrayList<>();

    ArrayAdapter<String> titleArrayAdapter1;
    ArrayAdapter<String> titleArrayAdapter2;

    RamadanPresenter ramadanPresenter;

    //widgets
    private ListView titleListView1,titleListView2;
    private Button nextBTN,prevBTN,backBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramadan);

        titleListView1=findViewById(R.id.titleListView1);
        titleListView2=findViewById(R.id.titleListView2);
        nextBTN=findViewById(R.id.nextBTN);
        prevBTN=findViewById(R.id.prevBTN);
        backBTN=findViewById(R.id.backBTN);

        ramadanPresenter=new RamadanPresenterImpl(this,this);
        ramadanPresenter.getDataFromDbForListView1();
        ramadanPresenter.getDataFromDbForListView2();

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleListView1.getVisibility()==View.VISIBLE){
                    titleListView1.setVisibility(View.GONE);
                    titleListView2.setVisibility(View.VISIBLE);
                }
            }
        });

        prevBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleListView2.getVisibility()==View.VISIBLE) {
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
    public void itemClickedCallBack1() {
        titleListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(titleList1!=null) {
                    String title = titleList1.get(position);
                    fireIntent(title);
                }else{
                    Toast.makeText(RamadanActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RamadanActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fireIntent(String title) {
        Intent intent = new Intent(RamadanActivity.this, DetailsActivity.class);
        intent.putExtra("TITLE_NAME", title);
        intent.putExtra("TABLE_NAME","romjan");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
