package com.safecornerscoffee.resurrection.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.util.Assert;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private Long id;

    @Min(4)
    private String name;

    @Past
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime joinedAt;

    protected User() {}

    public User(String name) {
        Assert.notNull(name, "name should be not null");
        Assert.hasText(name, "name should be not empty");

        this.name = name;
    }

    public User(Long id, String name, LocalDateTime joinedAt) {

        Assert.notNull(id, "id should not be null");
        Assert.notNull(name, "name should be not null");
        Assert.hasText(name, "name should be not empty");
        Assert.notNull(joinedAt, "joinedAt should not be null");

        this.id = id;
        this.name = name;
        this.joinedAt = joinedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
                Objects.equals(this.getName(), user.getName()) &&
                Objects.equals(this.getJoinedAt(), user.getJoinedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this, id, this.name, this.joinedAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
