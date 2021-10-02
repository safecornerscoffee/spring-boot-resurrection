package com.safecornerscoffee.resurrection.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Spy
    UserRepository userRepository;

    @Test
    public void should_save_user() {

        User savedUser = userService.save(new User("Emma Stone#1"));

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isNotNull();
        assertThat(savedUser.getJoinedAt()).isNotNull();

        userRepository.delete(savedUser);
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

        userRepository.delete(found);
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