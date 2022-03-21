package com.example.banking_app.service;

import com.example.banking_app.model.User;
import com.example.banking_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long user_id) {
        return userRepository.getById(user_id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }
}
