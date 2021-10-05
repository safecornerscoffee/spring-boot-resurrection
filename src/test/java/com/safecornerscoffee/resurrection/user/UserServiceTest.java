package com.safecornerscoffee.resurrection.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void should_save_user() {
        //given
        User user = new User("common-sense", "resurrection");
        doAnswer(invocationOnMock -> {
            AtomicLong nextId = new AtomicLong();
            User storeUser = invocationOnMock.getArgument(0, User.class);
            storeUser.setId(nextId.incrementAndGet());
            return storeUser;
        }).when(userRepository).save(any(User.class));

        //when
        User savedUser = userRepository.save(user);
        //then
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void should_fetch_exists_user() {
        //given
        User user = new User("common-sense", "resurrection");
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));

        //when
        User found = userService.findById(1L);

        //that
        assertThat(found).isEqualTo(user);
    }
    
    @Test
    void should_throw_exception_fetching_none_exists_user() {
        Long userId = 1L;
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> {
            userService.findById(userId);
        }).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void should_delete_exists_user() {
        //given
        Long userId = 1L;
        User user = new User("common-sense", "resurrection");
        user.setId(userId);
        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        //when
        User deletedUser = userService.deleteById(user.getId());

        //then
        assertThat(deletedUser).isEqualTo(user);
    }

    @Test
    void throw_exception_deleting_none_exists_user() {
        //given
        Long userId = 1L;
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> {
            userService.deleteById(userId);
        }).isInstanceOf(UserNotFoundException.class);
    }


}