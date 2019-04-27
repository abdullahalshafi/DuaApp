package com.bean.coffee.duaapp.View;

import java.util.List;

public interface RamadanView {

    void initUi();
    void onClickListeners();

    void populateListView1(List<String> titleList);
    void populateListView2(List<String> titleList);

    void itemClickedCallBack();
}
