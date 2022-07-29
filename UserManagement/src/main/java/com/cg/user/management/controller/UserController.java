package com.cg.user.management.controller;

import com.cg.user.management.dto.User;
import com.cg.user.management.exception.UserNotFoundException;
import com.cg.user.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    //create user
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){

        logger.info("Executing createUser method");
        User newUser = userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //Update user
    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public User updateUser(@RequestBody User user){
        logger.info("Executing updateUser method");
        Optional<User> userById = userService.getUserById(user.getId());
        if(userById.isPresent()){
            //password cannot be updated, hence overriding
            user.setPassword(userById.get().getPassword());
            return userService.updateUser(user);
        }else{
            throw new UserNotFoundException(getMessage("user.not.found",user.getId().toString()));
        }
    }

    //fetch all users
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getAllUsers(){
        logger.info("Executing getAllUsers method");
        return userService.getAllUsers();
    }

    //fetch user by id
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public User getUserById(@PathVariable String id){
        logger.info("Executing getUserById method with id - "+id);
        Optional<User> user = userService.getUserById(Long.valueOf(id));
        if(user.isPresent()){
            return user.get();
        }else{
            throw new UserNotFoundException(getMessage("user.not.found",id));
        }
    }

    //fetch user by firstname
    @RequestMapping(method = RequestMethod.GET, value = "/users/firstname/{firstname}")
    public List<User> getUserByFirstName(@PathVariable(name = "firstname") String firstName){
        logger.info("Executing getUserByFirstName method with firstname - "+firstName);
        List<User> usersByFirstName = userService.getUserByFirstName(firstName);
        if(usersByFirstName.size()>0){
            return usersByFirstName;
        }else{
            throw new UserNotFoundException(getMessage("user.not.found.firstname",firstName));
        }
    }

    //fetch user by lastname
    @RequestMapping(method = RequestMethod.GET, value = "/users/lastname/{lastname}")
    public List<User> getUserByLastName(@PathVariable(name = "lastname") String lastName){
        logger.info("Executing getUserByLastName method with lastname - "+lastName);
        List<User> usersByLastName = userService.getUserByLastName(lastName);
        if(usersByLastName.size()>0){
            return usersByLastName;
        }else{
            throw new UserNotFoundException(getMessage("user.not.found.lastname",lastName));
        }
    }

    //fetch user by email
    @RequestMapping(method = RequestMethod.GET, value = "/users/email/{email}")
    public User getUserByEmail(@PathVariable(name = "email") String email){
        logger.info("Executing getUserByEmail method with email - "+email);
        User userByEmail = userService.getUserByEmail(email);
        if(userByEmail!=null){
            return userByEmail;
        }else{
            throw new UserNotFoundException(getMessage("user.not.found.email",email));
        }
    }

    //delete user by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUserById(@PathVariable String id){
        userService.deleteUserById(Long.valueOf(id));
    }

    //delete user by firstname
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/firstname/{firstname}")
    public void deleteUserByFirstName(@PathVariable(name = "firstname") String firstName){
        Long noOfUsersDeleted = userService.deleteUserByFirstName(firstName);
        logger.info("No. of Users deleted -"+noOfUsersDeleted);
    }

    //delete user by lastname
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/lastname/{lastname}")
    public void deleteUserByLastName(@PathVariable(name = "lastname") String lastName){
        Long noOfUsersDeleted = userService.deleteUserByLastName(lastName);
        logger.info("No. of Users deleted -"+noOfUsersDeleted);
    }

    //delete user by email
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/email/{email}")
    public void deleteUserByEmail(@PathVariable(name = "email") String email){
        Long noOfUsersDeleted = userService.deleteUserByEmail(email);
        logger.info("No. of Users deleted -"+noOfUsersDeleted);
    }

    public String getMessage(String messageKey, String... args){
        return messageSource.getMessage(messageKey, args, Locale.ENGLISH);
    }
}
