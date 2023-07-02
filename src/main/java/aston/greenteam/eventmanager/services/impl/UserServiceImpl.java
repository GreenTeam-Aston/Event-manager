package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.UserDTO;
import aston.greenteam.eventmanager.dtos.UserDTORegister;
import aston.greenteam.eventmanager.dtos.UserFriendDTO;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.entities.UserRole;
import aston.greenteam.eventmanager.repositories.UserRepository;
import aston.greenteam.eventmanager.repositories.UserRoleRepository;
import aston.greenteam.eventmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user){
        UserRole userRole = userRoleRepository.findByUserRole("ROLE_USER");
        user.setUserRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void addFriends(Long thisUserId, Long anotherUserId){
        User thisUser = userRepository.findById(thisUserId).orElseThrow();
        User anotherUser = userRepository.findById(anotherUserId).orElseThrow();
        addUserFriend(thisUser,anotherUser);
    }

    public void addUserFriend(User thisUser, User anotherUser){
        if(thisUser.getFriends().contains(anotherUser)){
            return;
        }
        thisUser.getFriends().add(anotherUser);
        anotherUser.getFriends().add(thisUser);
        userRepository.save(thisUser);
        userRepository.save(anotherUser);
    }

    public void deleteFriends(Long thisUserId, Long anotherUserId){
        User thisUser = userRepository.findById(thisUserId).orElseThrow();
        User anotherUser = userRepository.findById(anotherUserId).orElseThrow();
        deleteUserFriend(thisUser,anotherUser);
    }

    public void deleteUserFriend(User thisUser, User anotherUser){
        thisUser.getFriends().remove(anotherUser);
        anotherUser.getFriends().remove(thisUser);
        userRepository.save(thisUser);
        userRepository.save(anotherUser);
    }

    public List<User> findFriendsById(Long userId){
        return userRepository.findAllFriends(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login).orElseThrow();
    }

    public UserDTORegister findByUserAndPassword(String login, String password){
        User userEntity = findByLogin(login);

        if(userEntity != null) {
            if(passwordEncoder.matches(password, userEntity.getPassword())){
                return convertToDTO( userEntity);
            }
        }
        return null;
    }

    public boolean existsUserByLogin(String login){
        return userRepository.existsUserByLogin(login);
    }

    public UserDTORegister convertToDTO(User user) {
        UserDTORegister userDTO = new UserDTORegister();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setNickname(user.getNickname());
        userDTO.setUserRoleString(user.getUserRole().getUserRole());
        return userDTO;
    }

    public UserDTO userToDTO (User user){
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .nickname(user.getNickname())
                .age(user.getAge())
                .gender(user.getGender())
                .build();
    }

    public UserFriendDTO userFriendToDTO(User user){
        return UserFriendDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .nickname(user.getNickname())
                .build();
    }


}
