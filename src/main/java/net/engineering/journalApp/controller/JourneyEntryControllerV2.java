package net.engineering.journalApp.controller;

import net.engineering.journalApp.entity.JournalEntry;
import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.service.JournalEntryService;
import net.engineering.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JourneyEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String useName = authentication.getName();
        List<JournalEntry> entries = journalEntryService.getEntriesOfUser(useName);
        if (entries == null || entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entries);
    }


    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        JournalEntry saved = journalEntryService.createEntry(entry, userName);
        if (saved == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get journal by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> entries = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());

        if (!entries.isEmpty()) {
            JournalEntry entry = journalEntryService.getById(id);
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete journal
    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id,
                                         @PathVariable String userName) {

        journalEntryService.deleteEntry(id, userName);

        return ResponseEntity.noContent().build();
    }

    // Update journal
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id,
                                         @RequestBody JournalEntry entry) {

        JournalEntry updated = journalEntryService.updateEntry(id, entry);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }
}