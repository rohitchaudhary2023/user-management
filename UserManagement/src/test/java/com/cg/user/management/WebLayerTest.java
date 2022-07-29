package com.cg.user.management;

import com.cg.user.management.controller.UserController;
import com.cg.user.management.dto.User;
import com.cg.user.management.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@TestPropertySource(properties =
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class WebLayerTest {

    @MockBean
    UserService userService;
	@Autowired
    MockMvc mockMvc;

    @Test
	public void shouldReturnListOfOneUser() throws Exception {
		List<User> users = Arrays.asList(new User(1L, "rohit", "chaudhary", "abc@xyz.com", "Test@123456"));

		when(userService.getAllUsers()).thenReturn(users);

		this.mockMvc.perform(get("/users"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void shouldReturnOneUser() throws Exception {
        User user = new User(1L, "rohit", "chaudhary", "abc@xyz.com", "Test@123456");

        when(userService.getUserById(Long.valueOf(1))).thenReturn(Optional.of(user));

        this.mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Matchers.is("rohit")));;
    }
}

