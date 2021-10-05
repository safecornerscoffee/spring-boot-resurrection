package com.safecornerscoffee.resurrection;

import com.safecornerscoffee.resurrection.user.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ResurrectionApplicationTests {

	@Autowired
	UserController userController;

	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
	}

}
