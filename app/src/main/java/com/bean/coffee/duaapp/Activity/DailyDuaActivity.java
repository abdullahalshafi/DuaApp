package com.bean.coffee.duaapp.Activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bean.coffee.duaapp.Class.MyCustomListViewAdapter;
import com.bean.coffee.duaapp.Model.DailyDuaPresenterImpl;
import com.bean.coffee.duaapp.Presenter.DailyDuaPresenter;
import com.bean.coffee.duaapp.R;
import com.bean.coffee.duaapp.View.DailyDuaView;

import java.util.ArrayList;
import java.util.List;

public class DailyDuaActivity extends AppCompatActivity implements DailyDuaView {

    //vars
    private List<String> dailyDuas = new ArrayList<>();
    MyCustomListViewAdapter dailyDuaAdapter;
    DailyDuaPresenter dailyDuaPresenter;
    private int btnStatus = 0;

    //widgets
    private ListView dailyDuaLv;
    private Button nextBtn, prevBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_dua);

        initUi();
        onClickListeners();
    }


    @Override
    public void initUi() {
        dailyDuaLv = findViewById(R.id.daily_dua_list_view);

        nextBtn = findViewById(R.id.daily_dua_next_btn);
        prevBtn = findViewById(R.id.daily_dua_prev_btn);
        backBtn = findViewById(R.id.daily_dua_back_btn);

        dailyDuaPresenter = new DailyDuaPresenterImpl(DailyDuaActivity.this, this);

        dailyDuaAdapter = new MyCustomListViewAdapter(this, dailyDuas);
        dailyDuaLv.setAdapter(dailyDuaAdapter);

        dailyDuaPresenter.getDataFromDbForListView1();
        btnStatus = 1;
    }

    @Override
    public void onClickListeners() {

        nextBtn.setOnClickListener(v -> {

            if (btnStatus >= 1 && btnStatus < 3) {
                btnStatus++;
                switch (btnStatus) {
                    case 2:
                        dailyDuaPresenter.getDataFromDbForListView2();
                        break;
                    case 3:
                        dailyDuaPresenter.getDataFromDbForListView3();
                        break;
                }
            }

        });

        prevBtn.setOnClickListener(v -> {

            if (btnStatus > 1 && btnStatus <= 3) {
                btnStatus--;

                switch (btnStatus) {
                    case 1:
                        dailyDuaPresenter.getDataFromDbForListView1();
                        break;
                    case 2:
                        dailyDuaPresenter.getDataFromDbForListView2();
                        break;
                }
            }

        });

        backBtn.setOnClickListener(v -> onBackPressed());

        itemClickedCallBack();

    }

    @Override
    public void populateListView1(List<String> duaList) {
        dailyDuas.clear();
        if (duaList.size() > 0) {
            dailyDuas = new ArrayList<>(duaList);
            dailyDuaAdapter.updateListData(dailyDuas);
        } else {
            Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void populateListView2(List<String> duaList) {
        dailyDuas.clear();
        if (duaList.size() > 0) {
            dailyDuas = new ArrayList<>(duaList);
            dailyDuaAdapter.updateListData(dailyDuas);
        } else {
            Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void populateListView3(List<String> duaList) {
        dailyDuas.clear();
        if (duaList.size() > 0) {
            dailyDuas = new ArrayList<>(duaList);
            dailyDuaAdapter.updateListData(dailyDuas);
        } else {
            Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void itemClickedCallBack() {

        dailyDuaLv.setOnItemClickListener((parent, view, position, id) -> {

            if (dailyDuas != null) {
                fireIntent(dailyDuas.get(position));
            } else {
                Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fireIntent(String title) {
        Intent intent = new Intent(DailyDuaActivity.this, DetailsActivity.class);
        intent.putExtra("TITLE_NAME", title);
        intent.putExtra("TABLE_NAME", "dua");
        startActivity(intent);
    }
}
