package net.engineering.journalApp.service;

import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setUserName("ishika");

        userService.saveUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        ObjectId id = new ObjectId();
        User user = new User();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(id);

        assertTrue(result.isPresent());
        verify(userRepository).findById(id);
    }

    @Test
    void testDeleteById() {
        ObjectId id = new ObjectId();

        userService.deleteById(id);

        verify(userRepository).deleteById(id);
    }

    @Test
    void testFindByUserName() {
        User user = new User();
        user.setUserName("ishika");

        when(userRepository.findByUserName("ishika")).thenReturn(user);

        User result = userService.findByUserName("ishika");

        assertEquals("ishika", result.getUserName());
    }

    @Test
    void testSaveAdmin() {
        User user = new User();
        user.setUserName("admin");

        userService.saveAdmin(user);

        verify(userRepository).save(user);
        assertTrue(user.getRoles().contains("ADMIN"));
    }
}
