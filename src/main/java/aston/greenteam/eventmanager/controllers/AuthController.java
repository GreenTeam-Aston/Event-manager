package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.api.JwtRequest;
import aston.greenteam.eventmanager.api.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/authentication")
    public JwtResponse createAuthToken(@RequestBody JwtRequest jwtRequest) {
        userService.auth(jwtRequest);
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        return new JwtResponse(userService.getToken(userDetails));
    }
}

