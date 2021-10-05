package com.safecornerscoffee.resurrection.user.data;

import com.safecornerscoffee.resurrection.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;

    public User toUser() {
        return new User(this.username, this.password);
    }
}
