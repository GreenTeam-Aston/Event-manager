package aston.greenteam.eventmanager.services;


import aston.greenteam.eventmanager.dtos.UserDTO;
import aston.greenteam.eventmanager.dtos.UserDTORegister;
import aston.greenteam.eventmanager.dtos.UserFriendDTO;
import aston.greenteam.eventmanager.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User findByLogin(String login);
    UserDTORegister findByUserAndPassword(String login, String password);
    boolean existsUserByLogin(String login);
    UserDTORegister convertToDTO(User user);
    List<User> findAll();
    UserDTO userToDTO (User user);
    UserFriendDTO userFriendToDTO(User user);
    List<User> findFriendsById(Long userId);
    void addFriends(Long thisUserId, Long anotherUserId);
    void deleteFriends(Long thisUserId, Long anotherUserId);
}