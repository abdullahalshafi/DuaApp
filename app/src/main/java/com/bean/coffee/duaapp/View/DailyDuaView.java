package com.bean.coffee.duaapp.View;


import java.util.List;

public interface DailyDuaView {


    void initUi();
    void onClickListeners();

    void populateListView1(List<String> duaList);
    void populateListView2(List<String> duaList);
    void populateListView3(List<String> duaList);

    void itemClickedCallBack();

}
