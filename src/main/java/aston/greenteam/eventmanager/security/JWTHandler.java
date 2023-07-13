package aston.greenteam.eventmanager.security;


import aston.greenteam.eventmanager.dtos.UserDTORegister;

public interface JWTHandler {
    String generateToken(UserDTORegister token);
    UserDTORegister parseToken(String token);
}
