package com.safecornerscoffee.resurrection.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User save(User user) {

        return repository.save(user);
    }

    @Transactional
    public User deleteById(Long id) {
        return repository.findById(id).map(user -> {
            repository.delete(user);
            return user;
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
}
