package net.engineering.journalApp.service;

import net.engineering.journalApp.entity.JournalEntry;
import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    // Get all entries of a user
    public List<JournalEntry> getEntriesOfUser(String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) return null;
        return user.getJournalEntries();
    }

    // Create entry
    @Transactional
    public JournalEntry createEntry(JournalEntry entry, String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) return null;

        entry.setDate(LocalDateTime.now());
        JournalEntry savedEntry = journalEntryRepository.save(entry);
        user.getJournalEntries().add(savedEntry);
        userService.saveUser(user);
        return savedEntry;
    }

    // Get entry by id
    public JournalEntry getById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    // Delete entry
    @Transactional
    public void deleteEntry(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        if (user != null) {
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
    }

    // Update entry
    public JournalEntry updateEntry(ObjectId id, JournalEntry newEntry) {
        JournalEntry old = journalEntryRepository.findById(id).orElse(null);
        if (old != null) {
            if (newEntry.getTitle() != null) {
                old.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null) {
                old.setContent(newEntry.getContent());
            }
            journalEntryRepository.save(old);
        }
        return old;
    }

}