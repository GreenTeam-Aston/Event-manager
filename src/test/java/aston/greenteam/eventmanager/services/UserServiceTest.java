package aston.greenteam.eventmanager.services;


import aston.greenteam.eventmanager.dtos.UserDTORegister;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.entities.UserRole;
import aston.greenteam.eventmanager.repositories.UserRepository;
import aston.greenteam.eventmanager.repositories.UserRoleRepository;
import aston.greenteam.eventmanager.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserRoleRepository userRoleRepository;

    @Mock
    PasswordEncoder passwordEncoder;


    @Test
    void saveUser(){
        UserDTORegister userDTORegister = new UserDTORegister();
        userDTORegister.setUserRoleString("ROLE_USER");
        userDTORegister.setLogin("Login");
        userDTORegister.setNickname("Nickname");
        userDTORegister.setPassword("Password");

        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setUserRole("ROLE_USER");
        when(userRoleRepository.findByUserRole("ROLE_USER")).thenReturn(userRole);
        when(passwordEncoder.encode(userDTORegister.getPassword())).thenReturn("encodePass");

        userService.saveUser(userDTORegister);

        verify(userRoleRepository, times(1)).findByUserRole("ROLE_USER");
        verify(passwordEncoder,times(1)).encode("Password");

    }


    @Test
    void addFriends(){
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        user1.setFriends(List.of(user2));
        user2.setFriends(List.of(user1));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        userService.addFriends(user1.getId(),user2.getId());

        verify(userRepository,times(1)).findById(1L);
        verify(userRepository,times(1)).findById(2L);
    }

    @Test
    void addUserFriend(){
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        user1.setFriends(new ArrayList<>());
        user2.setFriends(new ArrayList<>());

        userService.addUserFriend(user1,user2);

        assertEquals(1,user1.getFriends().size());
        assertEquals(1,user2.getFriends().size());
        assertEquals(user2,user1.getFriends().get(0));
        assertEquals(user1,user2.getFriends().get(0));

        verify(userRepository,times(1)).save(user1);
        verify(userRepository,times(1)).save(user2);

    }


    @Test
    void deleteUserFriend(){
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        user1.setFriends(new ArrayList<>());
        user1.getFriends().add(user2);
        user2.setFriends(new ArrayList<>());
        user2.getFriends().add(user1);

        userService.deleteUserFriend(user1, user2);

        assertEquals(0,user1.getFriends().size());
        assertEquals(0,user2.getFriends().size());

    }

    @Test
    void findFriendsById(){
        User user = new User();
        user.setId(1L);

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.findAll();

        verify(userRepository, times(1)).findAll();

        assertEquals(1, users.size());
    }

    @Test
    void findByLogin(){
        User user = new User();
        user.setId(1L);
        user.setLogin("Login");

        when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

        userService.findByLogin(user.getLogin());

        verify(userRepository,times(1)).findByLogin(user.getLogin());

    }











}
