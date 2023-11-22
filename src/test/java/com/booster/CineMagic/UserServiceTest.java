package com.booster.CineMagic;

import com.booster.CineMagic.Entity.User;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Repository.IUserRepository;
import com.booster.CineMagic.Service.IUserService;
import com.booster.CineMagic.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    UserServiceTest(){

    }

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User userTest;
    private List<User> userListTest;

    @BeforeEach()
    void setUp(){
        MockitoAnnotations.initMocks(this);
        userListTest = new ArrayList<>();
        userTest = new User(1, "Pedro Martinez", "pedromtz@gmail.com", 26,
                        "PeterMtz", "12345", Account.PREMIUM);
        userListTest.add(userTest);

        when(userRepository.findAll()).thenReturn(userListTest);
    }

    @Test
    void testGetUsers(){
        List<User> userList = userService.getUsers();

        assertNotNull(userList);
    }

    

}
