package org.manish.authentication.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.manish.authentication.Service.JWTService;
import org.manish.authentication.entity.User;
import org.manish.authentication.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JWTService jwtService ;
    private final UserRepository userRepository;

    public JwtFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        logger.info("Authentication started::::::");
        logger.info("Auth Header present: {}"+ (authHeader != null));
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            try {
                String username = jwtService.extractUsername(token);
                logger.info("Extracted username from token: " + username);
    //             remiving this "&& SecurityContextHolder.getContext().getAuthentication() == null" as in many spring setups Spring sets this before filters
                if (username != null ) {
                    User user = userRepository.findByUsername(username);
                    if (user == null) {
                        logger.warn("User not found for username: " + username);
                        filterChain.doFilter(request, response);
                        return;
                    }
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    //            tells spring about user
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            } catch (Exception e) {
                logger.error("Invalid or expired JWT: {}"+ e.getMessage());
//                removing this throw as if invalid jwt i don't want my system to crash just log and continue
//                throw new RuntimeException(e);
            }

        }
        filterChain.doFilter(request,response);
    }
}
