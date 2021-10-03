package com.safecornerscoffee.resurrection.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findOne(Long id) {
        return repository.findOne(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User deleteById(Long id) {
        return repository.findOne(id).map(user -> {
            repository.delete(user);
            return user;
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
