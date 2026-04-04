package net.engineering.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Jounnal APIs")
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

    // Get journal by ID for a user
    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        boolean exists = user.getJournalEntries()
                .stream()
                .anyMatch(x -> x.getId().equals(id));
        if(exists) {
            JournalEntry entry = journalEntryService.getById(id);
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete journal for a user
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryService.deleteEntry(id, userName);
        return ResponseEntity.noContent().build();
    }

    // Update journal by ID for a user
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id,
                                         @RequestBody JournalEntry entry) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        boolean exists = user.getJournalEntries()
                .stream()
                .anyMatch(x -> x.getId().equals(id));

        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        JournalEntry updated = journalEntryService.updateEntry(id, entry, userName);

        return ResponseEntity.ok(updated);
    }
}