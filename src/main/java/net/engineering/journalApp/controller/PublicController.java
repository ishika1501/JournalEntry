package net.engineering.journalApp.controller;

import net.engineering.journalApp.entity.User;
import net.engineering.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }
    @PostMapping
    public void CreateUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
