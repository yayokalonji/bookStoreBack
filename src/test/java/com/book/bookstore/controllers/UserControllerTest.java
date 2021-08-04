package com.book.bookstore.controllers;

import com.book.bookstore.dtos.JwtRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldGenerateAuthToken() throws Exception {
        JwtRequestDTO jwtRequestDTO = new JwtRequestDTO();
        jwtRequestDTO.setPassword("password");
        jwtRequestDTO.setUsername("admin");
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGenerateAuthTokenUserException() throws Exception {
        JwtRequestDTO jwtRequestDTO = new JwtRequestDTO();
        jwtRequestDTO.setPassword("password");
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequestDTO)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldGenerateAuthTokenPassException() throws Exception {
        JwtRequestDTO jwtRequestDTO = new JwtRequestDTO();
        jwtRequestDTO.setUsername("admin");
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequestDTO)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldGenerateAuthFailToken() throws Exception {
        JwtRequestDTO jwtRequestDTO = new JwtRequestDTO();
        jwtRequestDTO.setPassword("passwords");
        jwtRequestDTO.setUsername("admin");
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequestDTO)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldGenerateAuthTokenPassUserException() throws Exception {
        JwtRequestDTO jwtRequestDTO = new JwtRequestDTO();
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequestDTO)))
                .andExpect(status().is4xxClientError());
    }
}
