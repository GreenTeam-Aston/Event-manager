package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.api.JwtRequest;
import aston.greenteam.eventmanager.api.RegistrationUserDto;
import aston.greenteam.eventmanager.entities.Role;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.repositories.UserRepository;
import aston.greenteam.eventmanager.services.UserService;
import aston.greenteam.eventmanager.utils.JWTTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JWTTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void auth(JwtRequest jwtRequest) {

    }

    @Override
    public void reg(RegistrationUserDto form) {
        User user = new User();
        user.setLogin(form.getLogin());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setNickname(form.getNickname());
        user.setAge(form.getAge());
        user.setGender(form.getGender());
        userRepository.save(user);
    }

    @Override
    public String getToken(UserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList());
    }
}
