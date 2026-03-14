package net.engineering.journalApp.controller;

import net.engineering.journalApp.entity.JournalEntry;
import net.engineering.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JourneyEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    // Get all journal entries of a user
    @GetMapping("/user/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUsers(@PathVariable String userName){

        List<JournalEntry> entries = journalEntryService.getEntriesOfUser(userName);

        if(entries == null || entries.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(entries);
    }

    // Create journal entry
    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry,
                                         @PathVariable String userName){

        JournalEntry saved = journalEntryService.createEntry(entry, userName);

        if(saved == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(201).body(saved);
    }

    // Get journal by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id){

        JournalEntry entry = journalEntryService.getById(id);

        if(entry == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(entry);
    }

    // Delete journal
    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id,
                                         @PathVariable String userName){

        journalEntryService.deleteEntry(id, userName);

        return ResponseEntity.noContent().build();
    }

    // Update journal
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id,
                                         @RequestBody JournalEntry entry){

        JournalEntry updated = journalEntryService.updateEntry(id, entry);

        if(updated == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }
}