package net.engineering.journalApp.controller;

import net.engineering.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class journeyEntryController {

    private Map<String, JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping("/entry")
    public List<JournalEntry> getAll(){
        return new ArrayList<>( journalEntryMap.values());
    }

//    @PostMapping()
//    public void postEntry(@RequestBody JournalEntry myEntry){
//        journalEntryMap.put(myEntry.getId(), myEntry);
//    }

    @GetMapping("/id/{id}")
    public JournalEntry findByID(@PathVariable long id){
        return journalEntryMap.get(id);
    }

    @DeleteMapping("/id/{id}")
    public JournalEntry deleteByID(@PathVariable long id){
        return journalEntryMap.remove(id);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable String id , @RequestBody JournalEntry myEntry){
        return journalEntryMap.put(id, myEntry);
    }
}
