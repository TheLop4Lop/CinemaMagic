package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.User;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.ExistingData;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    List<User> getUsersByAccountType(Account type);
    User getUserById(Integer id);
    ExistingData checkExistingUser(User compareUser);
    User addNewUser(User newUser);
    User modifyUserById(Integer id, User modifyUser);
    void deleteUserById(Integer id);

}
