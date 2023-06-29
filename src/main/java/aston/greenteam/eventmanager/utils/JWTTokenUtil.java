package aston.greenteam.eventmanager.utils;

import aston.greenteam.eventmanager.dtos.UserDTO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JWTTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Integer jwtLifetime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public UserDTO parseToken (String token) throws ExpiredJwtException {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(claimsJws.getBody().get("id",Long.class));
        userDTO.setLogin(claimsJws.getBody().get("sub",String.class));
        userDTO.setUserRoleString(claimsJws.getBody().get("role", String.class));

        System.out.println("Роли с parse token =" + userDTO.getUserRoleString());
        return userDTO;
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("role", List.class);
    }

}
