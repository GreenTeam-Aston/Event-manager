package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.api.JwtRequest;
import aston.greenteam.eventmanager.api.RegistrationUserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    void auth(JwtRequest jwtRequest);

    void reg(RegistrationUserDto form);

    String getToken(UserDetails userDetails);
}
