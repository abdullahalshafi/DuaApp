package com.bean.coffee.duaapp.Activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bean.coffee.duaapp.Class.MyCustomListViewAdapter;
import com.bean.coffee.duaapp.Model.RamadanPresenterImpl;
import com.bean.coffee.duaapp.Presenter.RamadanPresenter;
import com.bean.coffee.duaapp.R;
import com.bean.coffee.duaapp.View.RamadanView;

import java.util.ArrayList;
import java.util.List;

public class RamadanActivity extends AppCompatActivity implements RamadanView {

    //vars
    private List<String> ramadanDuas = new ArrayList<>();
    MyCustomListViewAdapter ramadanDuaAdapter;

    RamadanPresenter ramadanPresenter;

    //widgets
    private ListView ramadanDuaLv;
    private Button nextBtn, prevBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramadan);
        initUi();
        onClickListeners();
    }

    @Override
    public void initUi() {

        ramadanDuaLv = findViewById(R.id.ramadan_dua_list_view);
        nextBtn = findViewById(R.id.ramadan_next_btn);
        prevBtn = findViewById(R.id.ramadan_prev_btn);
        backBtn = findViewById(R.id.ramadan_back_btn);

        ramadanPresenter = new RamadanPresenterImpl(this, this);

        ramadanDuaAdapter = new MyCustomListViewAdapter(this, ramadanDuas);
        ramadanDuaLv.setAdapter(ramadanDuaAdapter);

        ramadanPresenter.getDataFromDbForListView1();
    }

    @Override
    public void onClickListeners() {
        nextBtn.setOnClickListener(v -> ramadanPresenter.getDataFromDbForListView2());
        prevBtn.setOnClickListener(v -> ramadanPresenter.getDataFromDbForListView1());
        backBtn.setOnClickListener(v -> onBackPressed());

        itemClickedCallBack();
    }

    @Override
    public void populateListView1(List<String> titleList) {
        ramadanDuas.clear();
        if (titleList.size() > 0) {
            ramadanDuas = new ArrayList<>(titleList);
            ramadanDuaAdapter.updateListData(ramadanDuas);
        } else {
            Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void populateListView2(List<String> titleList) {
        ramadanDuas.clear();
        if (titleList.size() > 0) {
            ramadanDuas = new ArrayList<>(titleList);
            ramadanDuaAdapter.updateListData(ramadanDuas);
        } else {
            Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void itemClickedCallBack() {
        ramadanDuaLv.setOnItemClickListener((parent, view, position, id) -> {
            if (ramadanDuas.size() > 0) {
                fireIntent(ramadanDuas.get(position));
            } else {
                Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fireIntent(String title) {
        Intent intent = new Intent(RamadanActivity.this, DetailsActivity.class);
        intent.putExtra("TITLE_NAME", title);
        intent.putExtra("TABLE_NAME", "romjan");
        startActivity(intent);
    }
}
