package com.alengeo.lfg.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.alengeo.lfg.R;
import com.alengeo.lfg.events.LockedEvent;
import com.alengeo.lfg.events.LockedEventListAdapter;
import com.alengeo.lfg.events.TentativeEvent;
import com.alengeo.lfg.events.TentativeEventListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<LockedEvent> upcomingEvents;
    private ArrayList<TentativeEvent> organizedEvents;
    private ArrayList<TentativeEvent> joinedEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadEventLists();
    }

    protected void loadEventLists() {
        upcomingEvents = new ArrayList<>();
        upcomingEvents.add(new LockedEvent("Title1", "Description1", 4));
        upcomingEvents.add(new LockedEvent("Title2", "Description2", 5));
        upcomingEvents.add(new LockedEvent("Title3", "Description3", 8));

        organizedEvents = new ArrayList<>();
        organizedEvents.add(new TentativeEvent("Title4", "Description4", 3, 5, 4));
        organizedEvents.add(new TentativeEvent("Title5", "Description5", 5, 8, 2));
        organizedEvents.add(new TentativeEvent("Title6", "Description6", 2, 4, 3));

        joinedEvents = new ArrayList<>();
        joinedEvents.add(new TentativeEvent("Title7", "Description7", 3, 5, 4));
        joinedEvents.add(new TentativeEvent("Title8", "Description8", 5, 8, 2));
        joinedEvents.add(new TentativeEvent("Title9", "Description9", 6, 12, 8));

        updateListLayout((LinearLayout) findViewById(R.id.upcoming_events),
                new LockedEventListAdapter(this, upcomingEvents));

        updateListLayout((LinearLayout) findViewById(R.id.organized_events),
                new TentativeEventListAdapter(this, organizedEvents));

        updateListLayout((LinearLayout) findViewById(R.id.joined_events),
                new TentativeEventListAdapter(this, joinedEvents));
    }

    protected void updateListLayout(LinearLayout layout, ListAdapter adapter) {
        final int adapterCount = adapter.getCount();
        for(int i = 0; i < adapterCount; ++i) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create) {

        } else if (id == R.id.nav_lfg) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_edit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
