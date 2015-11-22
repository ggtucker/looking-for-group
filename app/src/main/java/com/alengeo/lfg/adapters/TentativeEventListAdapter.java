package com.alengeo.lfg.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alengeo.lfg.R;
import com.alengeo.lfg.models.TentativeEvent;

import java.util.List;

public class TentativeEventListAdapter extends ArrayAdapter<TentativeEvent> {

    private final LayoutInflater inflater;

    public TentativeEventListAdapter(Activity context, List<TentativeEvent> entries) {
        super(context, R.layout.event_list_tentative, entries);
        this.inflater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view == null) {
            view = inflater.inflate(R.layout.event_list_tentative, null, true);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TentativeEvent entry = getItem(position);
        viewHolder.titleView.setText(entry.getTitle());
        viewHolder.descriptionView.setText(entry.getDescription());
        viewHolder.peopleRangeView.setText(entry.getPeopleRange());
        viewHolder.numPeopleView.setText(""+entry.getNumPeople());

        return view;
    }

    private class ViewHolder {
        public TextView titleView;
        public TextView descriptionView;
        public TextView peopleRangeView;
        public TextView numPeopleView;

        public ViewHolder(View view) {
            titleView = (TextView) view.findViewById(R.id.title);
            descriptionView = (TextView) view.findViewById(R.id.description);
            peopleRangeView = (TextView) view.findViewById(R.id.people_range);
            numPeopleView = (TextView) view.findViewById(R.id.num_people);
        }
    }
}