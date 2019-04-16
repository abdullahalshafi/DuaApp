package com.bean.coffee.duaapp.Presenter;

import com.bean.coffee.duaapp.Class.TodoModel;

public interface TodoPresenter {

    void insertTodoData(TodoModel todoModel);
    void deleteTodoData(int id);
    void getTodoData(String date);

}
