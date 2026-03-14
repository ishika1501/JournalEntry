package net.engineering.journalApp.controller;


import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create-admin-user")
    public void CreateAdmin(@RequestBody User user) {
       userService.saveAdmin(user);
    }
}
