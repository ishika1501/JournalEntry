package net.engineering.journalApp.service;

import net.engineering.journalApp.entity.JournalEntry;
import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.repository.JournalEntryRepository;
import net.engineering.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class UserEntryService {

        @Autowired
        private UserEntryRepository userEntryRepository;

        public void saveEntry(User user){
            userEntryRepository.save(user);
        }

        public List<User> getAll() {
            return userEntryRepository.findAll();
        }

        public Optional<User> findById(ObjectId id){
            return userEntryRepository.findById(id);
        }

        public JournalEntry deleteById(ObjectId id){
            userEntryRepository.deleteById(id);
            return null;
        }
    }

}
