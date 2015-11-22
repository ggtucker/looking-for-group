package com.alengeo.lfg.activities;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.alengeo.lfg.R;
import com.alengeo.lfg.client.BackendApiClient;
import com.alengeo.lfg.models.Location;
import com.alengeo.lfg.models.LockedEvent;
import com.alengeo.lfg.models.TentativeEvent;
import com.alengeo.lfg.models.User;
import com.alengeo.lfg.services.EventService;
import com.alengeo.lfg.sessions.SessionManager;
import com.alengeo.lfg.util.LFGCallback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CreateGroupActivity extends AppCompatActivity {

    private static final int DAY_MILLISECONDS = 86400000;
    private static final int HOUR_MILLISECONDS = 3600000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        SessionManager sessionManager = new SessionManager(getApplicationContext());

        registerDateTimeMinMaxAdapters();
        registerMinMaxSpinners(getResourceList(R.array.num_people), R.id.min_people, R.id.max_people);
        registerMinMaxSpinners(getResourceList(R.array.age_range), R.id.min_age, R.id.max_age);

        registerButtonCallback(sessionManager);
    }

    private void registerButtonCallback(final SessionManager sessionManager) {
        final Button createButton = (Button) findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LockedEvent lockedEvent = new LockedEvent();
                lockedEvent.setCategory(getSpinnerString(R.id.category));
                lockedEvent.setTitle(getTextFieldValue(R.id.title));
                lockedEvent.setDescription(getTextFieldValue(R.id.description));
                lockedEvent.setLocation(new Location(getTextFieldValue(R.id.location)));

                String tz = " " + TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT, Locale.getDefault());
                String startTime = formatDateTime(getSpinnerString(R.id.start_date), getSpinnerString(R.id.start_time)) + tz;
                String endTime = formatDateTime(getSpinnerString(R.id.end_date), getSpinnerString(R.id.end_time)) + tz;
                String lockTime = getSpinnerString(R.id.lock_time) + tz;
                lockedEvent.setStartTime(startTime);
                lockedEvent.setEndTime(endTime);
                lockedEvent.setLockTime(lockTime);

                lockedEvent.setMinPeople(getSpinnerInt(R.id.min_people));
                lockedEvent.setMaxPeople(getSpinnerInt(R.id.max_people));
                lockedEvent.setMinAge(getSpinnerInt(R.id.min_age));
                lockedEvent.setMaxAge(getSpinnerInt(R.id.max_age));

                EventService.createEvent(lockedEvent, sessionManager, new LFGCallback<TentativeEvent>() {
                    @Override
                    public void execute(TentativeEvent event) {
                        User user = sessionManager.getUser();
                        user.getOrganizedEvents().add(event);
                        sessionManager.persistUser(user);
                        Intent intent = new Intent(CreateGroupActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    public Date getDate(String date, String time) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy h:mm a");
        try {
            return df.parse(formatDateTime(date, time));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String formatDateTime(String date, String time) {
        return date + " " + time;
    }

    public String getTextFieldValue(@IdRes int id) {
        EditText view = (EditText) findViewById(id);
        return view.getText().toString();
    }

    public String getSpinnerString(@IdRes int id) {
        Spinner spinner = (Spinner) findViewById(id);
        return (String) spinner.getSelectedItem();
    }

    public int getSpinnerInt(@IdRes int id) {
        Spinner spinner = (Spinner) findViewById(id);
        return Integer.parseInt((String) spinner.getSelectedItem());
    }

    private void registerMinMaxSpinners(final List<String> minEntries, @IdRes int minId, @IdRes int maxId) {
        final List<String> maxEntries = new ArrayList<>(minEntries);

        final ArrayAdapter<String> minAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, minEntries);

        final ArrayAdapter<String> maxAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, maxEntries);

        final Spinner minSpinner = (Spinner) findViewById(minId);
        final Spinner maxSpinner = (Spinner) findViewById(maxId);

        minSpinner.setAdapter(minAdapter);
        maxSpinner.setAdapter(maxAdapter);

        minSpinner.setOnItemSelectedListener(
                createSpinnerSelectedListener(maxSpinner, minEntries, maxEntries, maxAdapter)
        );
    }

    private void registerDateTimeMinMaxAdapters() {
        // Configure min and max time and date spinner adapters
        final List<String> minDateEntries = getOneWeekOfDatesFromToday();
        final List<String> maxDateEntries = new ArrayList<>(minDateEntries);

        final List<String> timeEntries = getResourceList(R.array.event_times);
        final List<String> minTimeEntries = new ArrayList<>(timeEntries);
        final List<String> maxTimeEntries = new ArrayList<>(timeEntries);

        final ArrayAdapter<String> minDateAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, minDateEntries);

        final ArrayAdapter<String> maxDateAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, maxDateEntries);

        final ArrayAdapter<String> minTimeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, minTimeEntries);

        final ArrayAdapter<String> maxTimeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, maxTimeEntries);

        final Spinner minDateSpinner = (Spinner) findViewById(R.id.start_date);
        final Spinner maxDateSpinner = (Spinner) findViewById(R.id.end_date);

        final Spinner minTimeSpinner = (Spinner) findViewById(R.id.start_time);
        final Spinner maxTimeSpinner = (Spinner) findViewById(R.id.end_time);

        minDateSpinner.setAdapter(minDateAdapter);
        maxDateSpinner.setAdapter(maxDateAdapter);

        minTimeSpinner.setAdapter(minTimeAdapter);
        maxTimeSpinner.setAdapter(maxTimeAdapter);

        final AdapterView.OnItemSelectedListener dateSelectedListener =
                createSpinnerSelectedListener(maxDateSpinner, minDateEntries, maxDateEntries, maxDateAdapter);

        final AdapterView.OnItemSelectedListener timeSelectedListener =
                createSpinnerSelectedListener(maxTimeSpinner, minTimeEntries, maxTimeEntries, maxTimeAdapter);

        // Configure lock time spinner adapter
        final List<String> lockHours = getResourceList(R.array.lock_times);
        final List<String> lockTimeEntries = new ArrayList<>();

        final ArrayAdapter<String> lockTimeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lockTimeEntries);
        final Spinner lockTimeSpinner = (Spinner) findViewById(R.id.lock_time);
        lockTimeSpinner.setAdapter(lockTimeAdapter);

        updateLockTimes(lockTimeSpinner, lockTimeAdapter, lockTimeEntries, minDateSpinner, minTimeSpinner, lockHours);
        updateEventTimes(timeEntries, minTimeEntries);
        minTimeAdapter.notifyDataSetChanged();

        minTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean sameDay = minDateSpinner.getSelectedItem()
                        .equals(maxDateSpinner.getSelectedItem());
                if (sameDay) {
                    timeSelectedListener.onItemSelected(parent, view, position+1, id);
                } else {
                    timeSelectedListener.onNothingSelected(parent);
                }
                updateLockTimes(lockTimeSpinner, lockTimeAdapter, lockTimeEntries, minDateSpinner, minTimeSpinner, lockHours);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                timeSelectedListener.onNothingSelected(parent);
            }
        });

        minDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dateSelectedListener.onItemSelected(parent, view, position, id);
                updateEventTimes(timeEntries, minTimeEntries);

                boolean sameDay = minDateSpinner.getSelectedItem()
                        .equals(maxDateSpinner.getSelectedItem());
                if (sameDay) {
                    timeSelectedListener.onItemSelected(parent, view, minTimeSpinner.getSelectedItemPosition(), id);
                } else {
                    timeSelectedListener.onNothingSelected(parent);
                }
                updateLockTimes(lockTimeSpinner, lockTimeAdapter, lockTimeEntries, minDateSpinner, minTimeSpinner, lockHours);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dateSelectedListener.onNothingSelected(parent);
            }
        });

        maxDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean sameDay = minDateSpinner.getSelectedItem()
                        .equals(maxDateSpinner.getSelectedItem());
                if (sameDay) {
                    timeSelectedListener.onItemSelected(parent, view, minTimeSpinner.getSelectedItemPosition(), id);
                } else {
                    timeSelectedListener.onNothingSelected(parent);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private AdapterView.OnItemSelectedListener createSpinnerSelectedListener(
            final Spinner maxSpinner, final List<String> minEntries, final List<String> maxEntries,
            final ArrayAdapter<String> maxAdapter) {

        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMax = (String) maxSpinner.getSelectedItem();
                List<String> newMaxEntries = minEntries.subList(position, minEntries.size());
                maxEntries.clear();
                maxEntries.addAll(newMaxEntries);
                maxAdapter.notifyDataSetChanged();
                int selectedEndIndex = 0;
                if (maxEntries.contains(selectedMax)) {
                    selectedEndIndex = maxAdapter.getPosition(selectedMax);
                }
                maxSpinner.setSelection(selectedEndIndex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(minEntries.size() != maxEntries.size()) {
                    String selectedMax = (String) maxSpinner.getSelectedItem();
                    maxEntries.clear();
                    maxEntries.addAll(minEntries);
                    maxAdapter.notifyDataSetChanged();
                    maxSpinner.setSelection(maxAdapter.getPosition(selectedMax));
                }
            }
        };
    }

    private void updateEventTimes(List<String> timeEntries, List<String> minTimeEntries) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date minTime = new Date(System.currentTimeMillis() + HOUR_MILLISECONDS);
        String minDate = df.format(minTime);
        minTimeEntries.clear();
        for(String timeEntry : timeEntries) {
            Date dateTime = getDate(minDate, timeEntry);
            if(dateTime.after(minTime)) {
                minTimeEntries.add(timeEntry);
            }
        }
    }

    private void updateLockTimes(Spinner lockTimeSpinner, ArrayAdapter<String> lockTimeAdapter,
                                         final List<String> lockTimes, Spinner minDateSpinner,
                                         Spinner minTimeSpinner, List<String> lockHours) {

        String startTimeString = formatDateTime(
                (String)minDateSpinner.getSelectedItem(),
                (String)minTimeSpinner.getSelectedItem());
        DateFormat df = new SimpleDateFormat("EEEE MM/dd/yy h:mm a");
        int selectedIndex = lockTimeSpinner.getSelectedItemPosition();
        Date currentTime = new Date(System.currentTimeMillis());
        try {
            long startTimestamp = df.parse(startTimeString).getTime();
            lockTimes.clear();
            for(String hourString : lockHours) {
                int hour = Integer.parseInt(hourString);
                Date lockTime = new Date(startTimestamp - HOUR_MILLISECONDS * hour);
                if(lockTime.after(currentTime)) {
                    lockTimes.add(df.format(lockTime));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lockTimeSpinner.setSelection(selectedIndex);
        lockTimeAdapter.notifyDataSetChanged();
    }

    private List<String> getOneWeekOfDatesFromToday() {
        List<String> dates = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("EEEE MM/dd/yy");
        Date date = new Date();

        for(int i = 0; i < 14; ++i) {
            dates.add(df.format(date));
            date.setTime(date.getTime() + DAY_MILLISECONDS);
        }

        return dates;
    }

    private List<String> getResourceList(@ArrayRes int arrayId) {
        String[] tempArray = getResources().getStringArray(arrayId);
        List<String> tempArrayList = Arrays.asList(tempArray);
        return new ArrayList<String>(tempArrayList);
    }
}
