package com.safecornerscoffee.resurrection.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class UserTest {

    @Test
    void should_throw_error_with_empty_name() {
        Long userId = 1L;
        String name = "";
        LocalDateTime joinDate = LocalDateTime.now();

        assertThatThrownBy(() -> {
            new User(userId, name, joinDate);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}