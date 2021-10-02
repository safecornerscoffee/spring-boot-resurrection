package com.safecornerscoffee.resurrection.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class UserTest {

    @Test
    public void CreateUser() {

    }

    @Test
    public void should_throw_error_with_empty_name() {
        assertThatThrownBy(() -> {
            new User(1L, "", LocalDateTime.now());
        }).isInstanceOf(IllegalArgumentException.class);
    }
}