package net.engineering.journalApp.repository;

import net.engineering.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

}
