package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.api.JwtResponse;
import aston.greenteam.eventmanager.api.RegistrationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public JwtResponse createAuthToken(@RequestBody RegistrationUserDto form) {
        userService.reg(form);
        UserDetails userDetails = userService.loadUserByUsername(form.getLogin());
        return new JwtResponse(userService.getToken(userDetails));
    }
}
