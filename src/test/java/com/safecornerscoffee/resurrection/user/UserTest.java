package com.safecornerscoffee.resurrection.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class UserTest {

    @Test
    void CreateUser() {

    }

    @Test
    void should_throw_error_with_empty_name() {
        assertThatThrownBy(() -> {
            new User(1L, "", LocalDateTime.now());
        }).isInstanceOf(IllegalArgumentException.class);
    }
}