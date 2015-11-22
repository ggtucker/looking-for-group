package com.alengeo.lfg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.alengeo.lfg.R;
import com.alengeo.lfg.adapters.TentativeEventListAdapter;
import com.alengeo.lfg.models.TentativeEvent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        String category = getIntent().getStringExtra("category");
        String eventsJson = getIntent().getStringExtra("events");
        Gson gson = new Gson();
        TentativeEvent[] events = gson.fromJson(eventsJson, TentativeEvent[].class);
        List<TentativeEvent> eventList = new ArrayList<>(Arrays.asList(events));

        TextView titleView = (TextView) findViewById(R.id.category_title);
        titleView.setText(category);

        updateListLayout((LinearLayout) findViewById(R.id.category_list),
                new TentativeEventListAdapter(this, eventList));
    }

    protected void updateListLayout(LinearLayout layout, ListAdapter adapter) {
        layout.removeAllViews();
        final int adapterCount = adapter.getCount();
        for(int i = 0; i < adapterCount; ++i) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }
    }
}
