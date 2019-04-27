package com.bean.coffee.duaapp.Model;

import android.content.Context;

import com.bean.coffee.duaapp.Class.TodoModel;
import com.bean.coffee.duaapp.Presenter.TodoPresenter;
import com.bean.coffee.duaapp.View.TodoView;

public class TodoPresenterImpl implements TodoPresenter {


    private TodoView todoView;
    private Context context;

    public TodoPresenterImpl(TodoView todoView, Context context) {
        this.todoView = todoView;
        this.context = context;
    }

    @Override
    public void insertTodoData(TodoModel todoModel) {

    }

    @Override
    public void deleteTodoData(int id) {

    }

    @Override
    public void getTodoData(String date) {

    }
}
