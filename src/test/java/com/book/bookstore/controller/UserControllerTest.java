package com.book.bookstore.controller;

import com.book.bookstore.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldGenerateAuthToken() throws Exception {
        User user = new User();
        user.setPassword("password");
        user.setUserName("username");
        MvcResult mvcResult = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();


        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\"}", "");

        System.out.println("Token:::::" + token);

        /*this.mockMvc.perform(get("/api/books").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());*/
    }
}
