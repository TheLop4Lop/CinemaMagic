package com.booster.CineMagic.Controller;

import com.booster.CineMagic.Entity.User;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.ExistingData;
import com.booster.CineMagic.Exception.*;
import com.booster.CineMagic.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user/v0")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<User> userCinema;

        try {
            userCinema = userService.getUsers();
        }catch (EmptyListException exception){
            System.out.println("Exception: No Users to GET");
            throw new EmptyListException("Not Found Exception", "Error 404, Empty List",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(userCinema);
    }

    @GetMapping("/users/account/{type}")
    public ResponseEntity<?> getUsersByAccount(@PathVariable Account type) {
        List<User> usersByAccount;

        try {
            usersByAccount = userService.getUsersByAccountType(type);
        }catch (NotFoundExceptionCinema exception){
            System.out.println("Exception: Not found, Incorrect Type");
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No Account found",
                        HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(usersByAccount);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        User userById;

        try{
            userById = userService.getUserById(id);
        }catch (NotFoundExceptionCinema exception){
            System.out.println("Exception: No Id found");
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(userById);
    }

    @PostMapping("/add/user")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser, BindingResult result){
        ExistingData existingUser = userService.checkExistingUser(newUser);

        if(result.hasErrors()){
            throw new EmptyDataListException("Empty Data List Exception", "Error 400", HttpStatus.BAD_REQUEST, result);
        }else if(existingUser != ExistingData.NULL){
            throw new ExistingDataException("Existing Data Exception: " + existingUser.toString(),
                            "Error 405", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(userService.addNewUser(newUser));
    }

    @PutMapping("/modify/user/{id}")
    public ResponseEntity<?> modifyUserById(@PathVariable Integer id, @Valid @RequestBody User modifyUser, BindingResult result){
        if(result.hasErrors()){
            throw new EmptyDataListException("Empty Data List Exception", "Error 404", HttpStatus.BAD_REQUEST, result);
        }

        ExistingData existingUser = userService.checkExistingUser(modifyUser);
        if(existingUser != ExistingData.NULL){
                throw new ExistingDataException("Existing Data Exception: " + existingUser.toString(),
                        "Error 409", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(userService.modifyUserById(id, modifyUser));
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Integer id) {
       User userToDelete;
       boolean result;

        try{
            userToDelete = userService.getUserById(id);
            result = userService.deleteUserById(userToDelete.getId());
        } catch (NotFoundExceptionCinema exception){
            System.out.println("Exception: No Id found");
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }

}
