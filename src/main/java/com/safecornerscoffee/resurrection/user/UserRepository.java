package com.safecornerscoffee.resurrection.user;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private static final Map<Long, User> users = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> findOne(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(sequence.incrementAndGet());
            user.setJoinedAt(LocalDateTime.now());
        }
        users.put(user.getId(), user);
        return user;
    }

    public User delete(User user) {
        users.remove(user.getId());
        return user;
    }
}
