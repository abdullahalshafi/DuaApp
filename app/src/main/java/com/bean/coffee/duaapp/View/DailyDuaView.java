package com.bean.coffee.duaapp.View;


import java.util.List;

public interface DailyDuaView {

    void populateListView1(List<String> titleList);
    void populateListView2(List<String> titleList);
    void populateListView3(List<String> titleList);

    void itemClickedCallBack1();
    void itemClickedCallBack2();
    void itemClickedCallBack3();

}
