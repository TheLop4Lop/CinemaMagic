package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.User;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.ExistingData;
import com.booster.CineMagic.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByAccountType(Account type) {
        List<User> allUsers = userRepository.findAll();
        List<User> usersType = new ArrayList<User>();

        if(type != Account.PREMIUM && type != Account.REGULAR){
            return null;
        }

        for(User singleUser: allUsers){
            if(singleUser.getAccount() == type){
                usersType.add(singleUser);
            }
        }

        return usersType;
    }

    @Override
    public User getUserById(Integer id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public ExistingData checkExistingUser(User compareUser) {
        List<User> allUsers = userRepository.findAll();

        for (User singleUser : allUsers){
            if(Objects.equals(singleUser.getUsername(), compareUser.getUsername())){
                return ExistingData.USERNAME;
            }else if (Objects.equals(singleUser.geteMail(), compareUser.geteMail())){
                return ExistingData.EMAIL;
            }
        }

        return ExistingData.NULL;
    }

    @Override
    public User addNewUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User modifyUserById(Integer id, User modifyUser) {
        if(userRepository.existsById(id)){
            getUserById(id).setName(modifyUser.getName());
            getUserById(id).seteMail(modifyUser.geteMail());
            getUserById(id).setAge(modifyUser.getAge());
            getUserById(id).setUsername(modifyUser.getUsername());
            getUserById(id).setPassword(modifyUser.getPassword());
            getUserById(id).setAccount(modifyUser.getAccount());

            return userRepository.save(getUserById(id));
        }

        return null;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        if(!userRepository.existsById(id)){
            return false;
        }

        userRepository.delete(getUserById(id));
        return true;
    }

}
