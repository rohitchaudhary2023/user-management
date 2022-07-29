package com.cg.user.management;

import com.cg.user.management.dto.User;
import com.cg.user.management.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoLayerTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUserCreateReadAndDelete() {
        User user = new User(1L, "rohit", "chaudhary",
                "abc@xyz.com", "Test@123456");

        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).extracting(User::getFirstName).containsOnly("rohit");

        userRepository.deleteAll();
        Assertions.assertThat(userRepository.findAll()).isEmpty();
    }
}
