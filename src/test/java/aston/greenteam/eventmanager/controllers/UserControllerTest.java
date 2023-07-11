package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.UserDTO;
import aston.greenteam.eventmanager.dtos.UserFriendDTO;
import aston.greenteam.eventmanager.entities.Bucket;
import aston.greenteam.eventmanager.entities.Contact;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.entities.UserRole;
import aston.greenteam.eventmanager.services.impl.UserServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @SneakyThrows
    @Test
    void getAllUsers(){

        User user = new User();
        user.setUserRole(new UserRole());
        user.setFriends(List.of(new User()));
        user.setBucket(List.of(new Bucket()));
        user.setEvents(List.of(new Event()));
        user.setContact((new Contact()));
        List<User> userList = List.of(user);

        when(userService.findAll()).thenReturn(userList);
        when(userService.userToDTO(user)).thenReturn(new UserDTO());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void getAllUserFriends(){

        User user = new User();
        when(userService.findFriendsById(1L)).thenReturn(List.of( user));
        when(userService.userFriendToDTO(user)).thenReturn(new UserFriendDTO());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1/friends")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void  addFriend(){

        doNothing().when(userService).addFriends(1L,2L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/friends")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("idUser", "1")
                        .param("idFriend", "2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void  removeFriend(){

        doNothing().when(userService).deleteFriends(1L,2L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/friends")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("idUser", "1")
                        .param("idFriend", "2"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
