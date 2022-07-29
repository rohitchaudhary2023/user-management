package com.cg.user.management.service;

import com.cg.user.management.dto.User;
import com.cg.user.management.exception.InputDataValidationException;
import com.cg.user.management.repository.UserRepository;
import com.cg.user.management.utils.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(Long userId){
        return userRepository.findById(userId);
    }

    public User createUser(User user){
        boolean valid = ValidationUtility.isValidPassword(user.getPassword());
        if(!valid){
            throw new InputDataValidationException("Password Validation failed");
        }

        User userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail!=null){
            throw new InputDataValidationException("User with email "+user.getEmail()+" already exists!");
        }

        String hashedPassword = securePassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    private String securePassword(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt(10));
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Long deleteUserByFirstName(String firstName) {
        return userRepository.deleteByFirstName(firstName);
    }

    public Long deleteUserByLastName(String lastName) {
        return userRepository.deleteByLastName(lastName);
    }

    public Long deleteUserByEmail(String email) {
        return userRepository.deleteByEmail(email);
    }
}
