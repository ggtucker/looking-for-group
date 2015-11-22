package com.alengeo.lfg.activities;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.alengeo.lfg.R;
import com.alengeo.lfg.adapters.CategoryListAdapter;
import com.alengeo.lfg.services.EventService;
import com.alengeo.lfg.sessions.SessionManager;
import com.alengeo.lfg.util.LFGCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupCategoriesActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_categories);
        this.sessionManager = new SessionManager(getApplicationContext());

        List<String> categories = getResourceList(R.array.categories);

        updateListLayout((LinearLayout) findViewById(R.id.categories),
                new CategoryListAdapter(this, categories));
    }

    protected void updateListLayout(LinearLayout layout, final ListAdapter adapter) {
        layout.removeAllViews();
        final int adapterCount = adapter.getCount();
        for(int i = 0; i < adapterCount; ++i) {
            final int index = i;
            View item = adapter.getView(i, null, null);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String category = (String)adapter.getItem(index);
                    EventService.getEventsByCategory(category, sessionManager,
                            new LFGCallback<String>() {
                        @Override
                        public void execute(String eventsJson) {
                            Intent intent = new Intent(GroupCategoriesActivity.this, GroupListActivity.class);
                            intent.putExtra("category", category);
                            intent.putExtra("events", eventsJson);
                            startActivity(intent);
                        }
                    });
                }
            });
            layout.addView(item);
        }
    }

    private List<String> getResourceList(@ArrayRes int arrayId) {
        String[] tempArray = getResources().getStringArray(arrayId);
        List<String> tempArrayList = Arrays.asList(tempArray);
        return new ArrayList<>(tempArrayList);
    }
}
