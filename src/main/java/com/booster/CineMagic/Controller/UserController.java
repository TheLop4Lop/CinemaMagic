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
        List<User> userCinema = userService.getUsers();

        if(userCinema.isEmpty())
        {
            throw new EmptyListException("Not Found Exception", "Error 404, User List is empty",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(userCinema);
    }

    @GetMapping("/users/account/{type}")
    public ResponseEntity<?> getUsersByAccount(@PathVariable Account type) {
        List<User> usersByAccount = userService.getUsersByAccountType(type);

        if(usersByAccount == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, Type not found",
                    HttpStatus.NOT_FOUND);
        }else if(usersByAccount.isEmpty()){
            throw new EmptyListException("Not Found Exception", "Error 404, User List is empty",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(usersByAccount);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        User userById = userService.getUserById(id);

        if(userById == null){
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
        }else if(userService.getUserById(id) == null){
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
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
        boolean deletionResult = userService.deleteUserById(id);

        if (!deletionResult) {
            throw new NotFoundExceptionCinema("Not Found Exception", "Error 404, No ID found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(true);
    }

}
