package com.alengeo.lfg.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alengeo.lfg.R;
import com.alengeo.lfg.models.TentativeEvent;

import java.util.List;

public class CategoryListAdapter extends ArrayAdapter<String> {

    private final LayoutInflater inflater;

    public CategoryListAdapter(Activity context, List<String> entries) {
        super(context, R.layout.category_list, entries);
        this.inflater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view == null) {
            view = inflater.inflate(R.layout.category_list, null, true);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String entry = getItem(position);
        viewHolder.categoryView.setText(entry);
        if(position % 2 == 0) {
            viewHolder.layoutView.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
        } else {
            viewHolder.layoutView.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        }

        return view;
    }

    private class ViewHolder {
        public LinearLayout layoutView;
        public ImageView iconView;
        public TextView categoryView;

        public ViewHolder(View view) {
            layoutView = (LinearLayout) view.findViewById(R.id.category_row);
            iconView = (ImageView) view.findViewById(R.id.category_icon);
            categoryView = (TextView) view.findViewById(R.id.category_title);
        }
    }
}