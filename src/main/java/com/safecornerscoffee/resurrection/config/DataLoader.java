package com.safecornerscoffee.resurrection.config;

import com.safecornerscoffee.resurrection.user.User;
import com.safecornerscoffee.resurrection.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataLoader {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User user = new User("mocha", "mocha");

                userService.save(user);
                logger.info("Executed CommandLineRunner");
            }
        };
    }
}
