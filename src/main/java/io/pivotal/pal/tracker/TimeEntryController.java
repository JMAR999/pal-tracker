package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }


    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = this.timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping(value = "/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntryReturnedFromFind = this.timeEntryRepository.find(timeEntryId);
        if (timeEntryReturnedFromFind != null){
            return new ResponseEntity<TimeEntry>(timeEntryReturnedFromFind, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = this.timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntries, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = this.timeEntryRepository.update(timeEntryId, timeEntry);
        if (updatedTimeEntry != null){
            return new ResponseEntity(updatedTimeEntry, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
        this.timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
