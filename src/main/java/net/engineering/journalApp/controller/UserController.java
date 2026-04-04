package net.engineering.journalApp.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.repository.UserRepository;
import net.engineering.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name="User APIs")
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @PutMapping("/{name}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String useName = authentication.getName();
        User userIndb = userService.findByUserName(name);
        userIndb.setUserName(user.getUserName());
        userIndb.setPassword(user.getPassword());
        userService.saveNewUser(userIndb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
