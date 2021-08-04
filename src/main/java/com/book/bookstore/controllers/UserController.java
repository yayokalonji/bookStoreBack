package com.book.bookstore.controllers;

import com.book.bookstore.dtos.JwtRequestDTO;
import com.book.bookstore.exceptions.ApiException;
import com.book.bookstore.services.impl.UserDetailsServiceImpl;
import com.book.bookstore.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class UserController {

    @Autowired
    JwtUtil jwtUtil;
    @Value("${book.app.jwtSecret}")
    private String jwtSecret;
    @Value("${book.app.jwtExpiration}")
    private int jwtExpiration;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Operation(summary = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return token",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @PostMapping("/user")
    public Map<String, String> login(@RequestBody JwtRequestDTO jwtRequestDTO) throws ApiException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequestDTO.getUsername(),
                            jwtRequestDTO.getPassword()
                    )
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequestDTO.getUsername());

            final String token = jwtUtil.generateToken(userDetails);
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return map;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new ApiException(HttpStatus.UNAUTHORIZED, exception.getMessage());
        }
    }
}
