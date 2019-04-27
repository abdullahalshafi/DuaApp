package com.bean.coffee.duaapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bean.coffee.duaapp.Class.TodoModel;
import com.bean.coffee.duaapp.R;
import com.bean.coffee.duaapp.View.TodoView;

import java.util.List;

public class TodoActivity extends AppCompatActivity implements TodoView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        initUi();
        onClickListeners();
    }

    @Override
    public void initUi() {

    }

    @Override
    public void onClickListeners() {

    }

    @Override
    public void populateListView(List<TodoModel> todoModels) {

    }

    @Override
    public void itemClickedCallBack() {

    }
}
