package com.bean.coffee.duaapp.View;

import com.bean.coffee.duaapp.Class.TodoModel;

import java.util.List;

public interface TodoView {

    void initUi();
    void onClickListeners();

    void populateListView(List<TodoModel> todoModels);

    void itemClickedCallBack();
}
