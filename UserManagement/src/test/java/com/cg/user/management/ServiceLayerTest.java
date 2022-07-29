package com.cg.user.management;

import com.cg.user.management.dto.User;
import com.cg.user.management.repository.UserRepository;
import com.cg.user.management.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceLayerTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testGetAllUsers()
    {
        List<User> users = Arrays.asList(new User(1L, "rohit", "chaudhary",
                "abc@xyz.com", "Test@123456"),
                new User(1L, "akash", "chopra",
                        "akash@xyz.com", "Test@123456"),
                new User(1L, "Ravi", "kumar",
                        "ravi@xyz.com", "Test@123456"));

        when(userRepository.findAll()).thenReturn(users);

        List<User> userList = userService.getAllUsers();

        assertEquals(3, userList.size());
        verify(userRepository, times(1)).findAll();
    }
}
