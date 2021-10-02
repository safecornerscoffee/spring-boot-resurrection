package com.safecornerscoffee.resurrection.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @Test
    void retrieve_all_user() throws Exception {
        userService.save(new User("Emma Stone"));
        userService.save(new User("Emily Blunt"));

        String responseBody = mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<User> users = objectMapper.readValue(responseBody, new TypeReference<List<User>>() {
        });

        assertThat(users).hasSize(2);
        users.forEach(user -> {
            userService.deleteById(user.getId());
        });
    }

    @Test
    void create_user() throws Exception {
        User newUser = new User("Emma Stone");
        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser));

        String responseBody = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User createdUser = objectMapper.readValue(responseBody, User.class);

        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getName()).isEqualTo(newUser.getName());
        assertThat(createdUser.getJoinedAt()).isNotNull();

        userService.deleteById(createdUser.getId());
    }

    @Test
    void retrieve_user() throws Exception {
        User user = userService.save(new User("Emma Stone"));

        String responseBody = mockMvc.perform(get("/users/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User fetchUser = objectMapper.readValue(responseBody, User.class);

        assertThat(fetchUser.getId()).isNotNull();
        assertThat(fetchUser.getName()).isEqualTo(user.getName());
        assertThat(fetchUser.getJoinedAt()).isNotNull();

        userService.deleteById(fetchUser.getId());
    }

    @Test
    void get_404_response_when_user_not_found() throws Exception {
        mockMvc.perform(get("/users/" + "999").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_user() throws Exception {
        User user = userService.save(new User("Emma Stone"));

        MockHttpServletRequestBuilder request = delete("/users/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    void delete_none_exists_user_should_throw_404_not_found() throws Exception {

        MockHttpServletRequestBuilder request = delete("/users/" + "999")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}