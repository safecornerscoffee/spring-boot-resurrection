package com.safecornerscoffee.resurrection.user;

import com.safecornerscoffee.resurrection.data.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<User> all() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public User one(@PathVariable Long id) {
        return service.findById(id);
    }


    @PostMapping(value = {"/users", "/api/signup"})
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        User user = dto.toUser();
        User savedUser = service.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(("/{id}"))
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
