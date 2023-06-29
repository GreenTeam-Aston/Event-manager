package aston.greenteam.eventmanager.utils;

import aston.greenteam.eventmanager.dtos.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JWTTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");


        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken authenticationToken = createToken(authorizationHeader);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader ){
        String token = authorizationHeader.replace("Bearer ", "");
        UserDTO userDTO = jwtTokenUtil.parseToken(token);
        System.out.println("роль из create token =" + userDTO.getUserRoleString());
        return new UsernamePasswordAuthenticationToken(userDTO, null, userDTO.getAuthorities() );
    }
}
