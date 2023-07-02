package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.JwtRequestDTO;
import aston.greenteam.eventmanager.dtos.JwtResponseDTO;
import aston.greenteam.eventmanager.dtos.UserDTORegister;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.security.JWTHandler;
import aston.greenteam.eventmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTHandler jwtHandler;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTORegister userDTO){

        if(userService.existsUserByLogin(userDTO.getLogin())){
            return ResponseEntity.of(Optional.of(HttpStatus.BAD_REQUEST.value()));
        }

        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setNickname(userDTO.getNickname());
        user.setPassword(userDTO.getPassword());

         userService.saveUser(user);

        return ResponseEntity.of(Optional.of(HttpStatus.OK.value()));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestDTO jwtRequestDTO){
        UserDTORegister userDTO = userService.findByUserAndPassword(jwtRequestDTO.getLogin(), jwtRequestDTO.getPassword());
        String token = jwtHandler.generateToken(userDTO);
        return ResponseEntity.ok(new JwtResponseDTO(token, userDTO.getId(), userDTO.getNickname()));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test (){
        return ResponseEntity.of(Optional.of(String.format("какой то тестовый метод")));
    }


}
