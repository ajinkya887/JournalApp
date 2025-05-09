package com.example.journalApp.controller;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.repository.JournalEntryRepository;
import com.example.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.JobHoldUntil;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAllEntries();
    }

    @PostMapping
    public String createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return "Journal Entry Added Successfully";
    }

    @GetMapping("/{myID}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myID){
        return journalEntryService.findById(myID).orElse(null);
    }

    @DeleteMapping("/{myID}")
    public String deleteJournalEntryById(@PathVariable ObjectId myID){
        journalEntryService.deleteById(myID);
        return "Deleted Sucessfully";
    }

    @PutMapping("/{myID}")
    public JournalEntry updateJournalById(@PathVariable ObjectId myID, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(myID).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
