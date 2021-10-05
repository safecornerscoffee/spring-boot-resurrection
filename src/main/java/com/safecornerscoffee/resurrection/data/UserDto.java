package com.safecornerscoffee.resurrection.data;

import com.safecornerscoffee.resurrection.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private static final String DEFAULT_ERROR_MESSAGE = "invalid username or password";

    @NotNull(message = DEFAULT_ERROR_MESSAGE)
    private String username;

    @NotNull(message = DEFAULT_ERROR_MESSAGE)
    private String password;

    public User toUser() {
        return new User(this.username, this.password);
    }
}
