package com.safecornerscoffee.resurrection.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void createUser() {
        User user = new User("username", "password");

        assertThat(user).hasNoNullFieldsOrPropertiesExcept("id");
    }
}