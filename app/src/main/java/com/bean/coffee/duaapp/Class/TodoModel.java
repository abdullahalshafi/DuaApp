package com.bean.coffee.duaapp.Class;

public class TodoModel {

    private int id;
    private String date;
    private String title;
    private boolean isCompleted;

    public TodoModel(int id, String date, String title, boolean isCompleted) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

