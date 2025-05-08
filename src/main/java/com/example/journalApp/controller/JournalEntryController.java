package com.example.journalApp.controller;

import com.example.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public ArrayList<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public String createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return "Journal Entry Added Successfully";
    }

    @GetMapping("/{myID}")
    public JournalEntry getJournalEntryById(@PathVariable Long myID){
        return journalEntries.get(myID);
    }

    @DeleteMapping("/{myID}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myID){
        return journalEntries.remove(myID);
    }

    @PutMapping("/{myID}")
    public JournalEntry updateJournalById(@PathVariable Long myID, @RequestBody JournalEntry myEntry){
        return journalEntries.put(myID, myEntry);
    }
}
