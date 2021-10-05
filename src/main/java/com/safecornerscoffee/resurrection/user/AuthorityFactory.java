package com.safecornerscoffee.resurrection.user;

import org.springframework.stereotype.Component;

@Component
public class AuthorityFactory {

    private final AuthorityRepository authorityRepository;

    private final Authority RoleUser;
    private final Authority RoleAdmin;

    public AuthorityFactory(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
        RoleUser = authorityRepository.findByName("ROLE_USER").orElseGet(() -> {
            return authorityRepository.save(new Authority("ROLE_USER"));
        });
        RoleAdmin = authorityRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            return authorityRepository.save(new Authority("ROLE_ADMIN"));
        });
    }

    public Authority getUserRole() {
        return RoleUser;
    }

    public Authority getAdminRole() {
        return RoleAdmin;
    }
}
