package com.bean.coffee.duaapp.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bean.coffee.duaapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyCustomListViewAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> duaList = new ArrayList<>();

    public MyCustomListViewAdapter(Context context, List<String> duaList) {
        super(context, R.layout.title_list_layout, duaList);
        this.duaList = duaList;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.title_list_layout, parent, false);
        }

        TextView txtView = itemView.findViewById(R.id.titleText);
        txtView.setText(duaList.get(position));

        return itemView;
    }

    public void updateListData(List<String> duaList) {
        this.duaList = duaList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return duaList.size();
    }
}
