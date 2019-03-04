package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    private final List<TimeEntry> timeEntries = new ArrayList<>();
    private long nextTimeEntryId = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        timeEntry.setTimeEntryId(nextTimeEntryId);
        nextTimeEntryId++;
        timeEntries.add(timeEntry);

        return timeEntry;
    }

    public TimeEntry find(long timeEntryId) {

        for (TimeEntry timeEntry : timeEntries) {
            if (timeEntry.getTimeEntryId() == timeEntryId){
                return  timeEntry;
            }
        }
        return null;
    }


    public List<TimeEntry> list() {
        return timeEntries;
    }

    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {

        TimeEntry timeEntryFound = find(timeEntryId);
        if (timeEntryFound != null){
            timeEntryFound.setProjectId(timeEntry.getProjectId());
            timeEntryFound.setDate(timeEntry.getDate());
            timeEntryFound.setHours(timeEntry.getHours());
            timeEntryFound.setUserId(timeEntry.getUserId());
        }

        return timeEntryFound;
    }



    public void delete(long timeEntryId) {

        TimeEntry timeEntry = find(timeEntryId);
        timeEntries.remove(timeEntry);

    }

}
