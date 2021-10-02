package com.safecornerscoffee.resurrection.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    UserService userService;

    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    public void should_save_user() {

        User savedUser = userService.save(new User("Emma Stone#1"));

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isNotNull();
        assertThat(savedUser.getJoinedAt()).isNotNull();
    }

    @Test
    public void should_fetch_exists_user() {
        //given
        User user = new User(1L, "Emma Stone#2", LocalDateTime.now());
        userRepository.save(user);

        //when
        User found = userService.findOne(user.getId());

        //that
        assertThat(found).isEqualTo(user);
    }
    
    @Test
    public void should_throw_exception_fetching_none_exists_user() {
        Long userId = 1L;

        assertThatThrownBy(() -> {
            userService.findOne(userId);
        }).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void should_delete_exists_user() {
        //given
        User user = new User(1L, "Emma Stone#3", LocalDateTime.now());
        userRepository.save(user);

        //when
        User deletedUser = userService.deleteById(user.getId());

        //then
        assertThat(deletedUser).isEqualTo(user);
    }

    @Test
    public void throw_exception_deleting_none_exists_user() {
        Long userId = 1L;

        assertThatThrownBy(() -> {
            userService.deleteById(userId);
        }).isInstanceOf(UserNotFoundException.class);
    }

}