package net.engineering.journalApp;

import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryImplTest {

    @Test
    void testUserList() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setSentimentAnalysis(true);

        List<User> users = List.of(user);

        assertEquals(1, users.size());
        assertEquals("test@example.com", users.get(0).getEmail());
        assertEquals(true, users.get(0).isSentimentAnalysis());
    }
}