package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.entities.UserRole;
import aston.greenteam.eventmanager.repositories.UserRepository;
import aston.greenteam.eventmanager.repositories.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;//TODO

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getUserRole()));
    } //преобразовываем User к UserDetails

    //маппим коллекцию ролей к коллекции SimpleGrantedAuthorities
    private Collection<GrantedAuthority> mapRolesToAuthorities(UserRole role) {
        return  List.of(new SimpleGrantedAuthority(role.getTitle()));
    }

}
