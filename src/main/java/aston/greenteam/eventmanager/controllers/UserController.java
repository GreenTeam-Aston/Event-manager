package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/test")
    public String getTest(){
        return "Hello asshole!";
    }


    @GetMapping("/get-all")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

}
