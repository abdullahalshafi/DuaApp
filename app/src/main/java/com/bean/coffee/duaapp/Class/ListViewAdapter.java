package com.bean.coffee.duaapp.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bean.coffee.duaapp.R;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> titleList;

    public ListViewAdapter(Context context, List<String> titleList) {
        super(context, R.layout.title_list_layout,titleList);

        this.titleList=titleList;
        this.context=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.title_list_layout, parent, false);
        }

        final String title=titleList.get(position);

        TextView txtView=(TextView) itemView.findViewById(R.id.titleText);
        txtView.setText(title);

        return itemView;
    }
}