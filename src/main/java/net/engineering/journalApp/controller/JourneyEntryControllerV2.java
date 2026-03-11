package net.engineering.journalApp.controller;

import net.engineering.journalApp.entity.JournalEntry;
import net.engineering.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JourneyEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/entry")
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping()
    public boolean postEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("/id/{id}")
    public JournalEntry findByID(@PathVariable ObjectId id){
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("/id/{id}")
    public JournalEntry deleteByID(@PathVariable ObjectId id){
        return journalEntryService.deleteById(id);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id , @RequestBody JournalEntry myEntry){

        JournalEntry old = journalEntryService.findById(id).orElse(null);

        if(old != null){

            if(myEntry.getTitle() != null){
                old.setTitle(myEntry.getTitle());
            }

            if(myEntry.getContent() != null){
                old.setContent(myEntry.getContent());
            }

            if(myEntry.getDate() != null){
                old.setDate(myEntry.getDate());
            }

            journalEntryService.saveEntry(old);
            return old;
        }

        return null;
    }

}
