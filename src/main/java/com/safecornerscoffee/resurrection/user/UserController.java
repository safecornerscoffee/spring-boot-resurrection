package com.safecornerscoffee.resurrection.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> all() {
        return service.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        User savedUser = service.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(("/{id}"))
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedUser);
    }

    @GetMapping("/users/{id}")
    public User one(@PathVariable Long id) {
        return service.findOne(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        User user = service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
