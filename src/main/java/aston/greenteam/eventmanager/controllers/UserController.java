package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.UserDTO;
import aston.greenteam.eventmanager.dtos.UserFriendDTO;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.services.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/test")
    public String getTest(){
        return "Hello my sweetest friend!";
    }


    @GetMapping("/get-all")
    public List<UserDTO> getAllUsers(){
        return userService.findAll()
                .stream()
                .map(userService::userToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get-all-friends/{id}")
    public List<UserFriendDTO> getAllUserFriends(@PathVariable Long id){
        return userService.findFriendsById(id)
                .stream()
                .map(userService::userFriendToDTO)
                .collect(Collectors.toList());
    }

    //todo добавить обработку различных результатов
    @PostMapping("/add-friend")
    public ResponseEntity<?> addFriend(@RequestParam Long idUser, @RequestParam Long idFriend){
        userService.addFriends(idUser,idFriend);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //todo добавить обработку различных результатов
    @DeleteMapping("/remove-friend")
    public ResponseEntity<?> removeFriend(@RequestParam Long idUser, @RequestParam Long idFriend){
        userService.deleteFriends(idUser,idFriend);
        return ResponseEntity.ok(HttpStatus.OK);
    }













}
