package com.alengeo.lfg.activities;

import android.content.Intent;
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
import com.alengeo.lfg.adapters.LockedEventListAdapter;
import com.alengeo.lfg.adapters.TentativeEventListAdapter;
import com.alengeo.lfg.models.User;
import com.alengeo.lfg.sessions.SessionManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.sessionManager = new SessionManager(getApplicationContext());

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

    @Override
    public void onResume() {
        super.onResume();
        loadEventLists();
    }

    protected void loadEventLists() {
        User user = sessionManager.getUser();

        updateListLayout((LinearLayout) findViewById(R.id.upcoming_events),
                new LockedEventListAdapter(this, user.getUpcomingEvents()));

        updateListLayout((LinearLayout) findViewById(R.id.organized_events),
                new TentativeEventListAdapter(this, user.getOrganizedEvents()));

        updateListLayout((LinearLayout) findViewById(R.id.joined_events),
                new TentativeEventListAdapter(this, user.getJoinedEvents()));
    }

    protected void updateListLayout(LinearLayout layout, ListAdapter adapter) {
        layout.removeAllViews();
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

        Intent intent = null;
        if (id == R.id.nav_create) {
            intent = new Intent(this, CreateGroupActivity.class);
        } else if (id == R.id.nav_lfg) {
            intent = new Intent(this, GroupCategoriesActivity.class);
        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_edit) {

        } else if (id == R.id.nav_logout) {
            sessionManager.logout();
        }

        if(intent != null) {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
