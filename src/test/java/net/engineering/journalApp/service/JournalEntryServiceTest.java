package net.engineering.journalApp.service;

import net.engineering.journalApp.entity.JournalEntry;
import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JournalEntryServiceTest {

    @Mock
    private JournalEntryRepository journalEntryRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private JournalEntryService journalEntryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test createEntry
    @Test
    void testCreateEntry() {
        User user = new User();
        user.setUserName("ishika");
        user.setJournalEntries(new ArrayList<>());

        JournalEntry entry = new JournalEntry();
        entry.setTitle("Test Entry");

        when(userService.findByUserName("ishika")).thenReturn(user);
        when(journalEntryRepository.save(entry)).thenReturn(entry);

        JournalEntry result = journalEntryService.createEntry(entry, "ishika");

        assertNotNull(result);
        assertEquals(1, user.getJournalEntries().size());
        verify(userService).saveUser(user);
        verify(journalEntryRepository).save(entry);
    }

    // Test getById
    @Test
    void testGetById() {
        ObjectId id = new ObjectId();
        JournalEntry entry = new JournalEntry();

        when(journalEntryRepository.findById(id)).thenReturn(Optional.of(entry));

        JournalEntry result = journalEntryService.getById(id);

        assertNotNull(result);
        verify(journalEntryRepository).findById(id);
    }

    // Test deleteEntry
    @Test
    void testDeleteEntry() {
        ObjectId id = new ObjectId();

        JournalEntry entry = new JournalEntry();
        entry.setId(id);

        User user = new User();
        user.setJournalEntries(new ArrayList<>());
        user.getJournalEntries().add(entry);

        when(userService.findByUserName("ishika")).thenReturn(user);

        journalEntryService.deleteEntry(id, "ishika");

        verify(journalEntryRepository).deleteById(id);
        verify(userService).saveUser(user);
    }

    // Test updateEntry
    @Test
    void testUpdateEntry() {
        ObjectId id = new ObjectId();

        JournalEntry oldEntry = new JournalEntry();
        oldEntry.setId(id);
        oldEntry.setTitle("Old");

        JournalEntry newEntry = new JournalEntry();
        newEntry.setTitle("New Title");

        User user = new User();
        user.setJournalEntries(new ArrayList<>());
        user.getJournalEntries().add(oldEntry);

        when(journalEntryRepository.findById(id)).thenReturn(Optional.of(oldEntry));
        when(journalEntryRepository.save(oldEntry)).thenReturn(oldEntry);
        when(userService.findByUserName("ishika")).thenReturn(user);

        JournalEntry result = journalEntryService.updateEntry(id, newEntry, "ishika");

        assertEquals("New Title", result.getTitle());
        verify(userService).saveUser(user);
        verify(journalEntryRepository).save(oldEntry);
    }
}