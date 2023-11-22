package com.booster.CineMagic;

import com.booster.CineMagic.Entity.User;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.ExistingData;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        when(userRepository.findById(1)).thenReturn(Optional.of(userTest));
        when(userRepository.existsById(1)).thenReturn(true);
    }

    @Test
    void testGetUsers(){
        List<User> userList = userService.getUsers();

        assertTrue(userList != null && !userList.isEmpty());
    }

    @Test
    void testGetUsersByAccountType(){
        List<User> userListAccount = userService.getUsersByAccountType(Account.PREMIUM);

        assertTrue(userListAccount != null && !userListAccount.isEmpty());
    }

    @Test
    void testGetUserById(){
        User userById = userService.getUserById(1);

        assertNotNull(userById);
    }

    @Test
    void testCheckExistingUser(){
        User compareUser = new User(2, "Rodrigo Martinez", "pedromtz@gmail.com", 22,
                "PeterMtz", "12345", Account.PREMIUM);
        ExistingData type = userService.checkExistingUser(compareUser);

        assertNotSame(type, ExistingData.NULL);
    }

    @Test
    void testAddNewUser(){
        User newUser = new User(2, "Rodrigo Martinez", "rodrimtz@gmail.com", 22,
                "RodriMtz", "12345", Account.REGULAR);

        when(userRepository.save(newUser)).thenReturn(newUser);
        User actualUser = userService.addNewUser(newUser);

        assertEquals(newUser, actualUser);
    }

    @Test
    void testModifyUserById(){
        User modifyUser = new User(1, "Rodrigo Martinez", "pedromtz@gmail.com", 22,
                "PeterMtz", "12345", Account.PREMIUM);

        when(userRepository.save(any(User.class))).thenReturn(modifyUser);
        User actualUser = userService.modifyUserById(1, modifyUser);

        assertEquals(modifyUser, actualUser);
    }



}
