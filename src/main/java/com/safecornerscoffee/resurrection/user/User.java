package com.safecornerscoffee.resurrection.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="user_authority",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="authority_name"))
    private Set<Authority> authorities = new HashSet<>();

    private static final Authority ROLE_USER = new Authority("ROLE_USER");

    @Past
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime joinedAt;

    protected User() {}

    public User(String username, String password) {
        Assert.notNull(username, "username should be not null");
        Assert.hasText(username, "username should be not empty");

        Assert.notNull(password, "password should be not null");
        Assert.hasText(password, "password should be not empty");

        this.username = username;
        this.password = password;
        this.authorities.add(ROLE_USER);
        this.joinedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return Objects.equals(this.getId(), user.getId()) &&
                Objects.equals(this.getUsername(), user.getUsername()) &&
                Objects.equals(this.getPassword(), user.getPassword()) &&
                Objects.equals(this.getAuthorities(), user.getAuthorities()) &&
                Objects.equals(this.getJoinedAt(), user.getJoinedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this, id, this.username, this.password, this.joinedAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
