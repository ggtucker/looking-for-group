package com.alengeo.lfg.util;

import com.alengeo.lfg.models.LockedEvent;
import com.alengeo.lfg.models.TentativeEvent;
import com.alengeo.lfg.models.Location;
import com.alengeo.lfg.models.User;
import com.alengeo.lfg.models.UserRatings;

import java.util.ArrayList;

public class TestObjects {
    public static User getUser() {
        ArrayList<LockedEvent> upcomingEvents = new ArrayList<>();
        upcomingEvents.add(new LockedEvent("Title1", "Description1", 4));
        upcomingEvents.add(new LockedEvent("Title2", "Description2", 5));
        upcomingEvents.add(new LockedEvent("Title3", "Description3", 8));

        ArrayList<TentativeEvent> organizedEvents = new ArrayList<>();
        organizedEvents.add(new TentativeEvent("Title4", "Description4", 3, 5, 4));
        organizedEvents.add(new TentativeEvent("Title5", "Description5", 5, 8, 2));
        organizedEvents.add(new TentativeEvent("Title6", "Description6", 2, 4, 3));

        ArrayList<TentativeEvent> joinedEvents = new ArrayList<>();
        joinedEvents.add(new TentativeEvent("Title7", "Description7", 3, 5, 4));
        joinedEvents.add(new TentativeEvent("Title8", "Description8", 5, 8, 2));
        joinedEvents.add(new TentativeEvent("Title9", "Description9", 6, 12, 8));

        return new User(
                "user_id",
                "10207823431206617",
                "description",
                new Location("city", "state"),
                "birthday",
                new UserRatings(),
                upcomingEvents,
                organizedEvents,
                joinedEvents
        );
    }
}
