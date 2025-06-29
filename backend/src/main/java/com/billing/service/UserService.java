package com.billing.service;

import com.billing.model.User;
import com.billing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            System.out.println("❌ User not found: " + username);
        } else {
            System.out.println("✅ User found: " + userOpt.get().getUsername());
            if (!userOpt.get().getPassword().equals(password)) {
                System.out.println("❌ Password mismatch for user: " + username);
            } else {
                System.out.println("✅ Login successful for user: " + username);
            }
        }
        return userOpt.filter(user -> user.getPassword().equals(password));
    }


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User register(User user) {
        return userRepository.save(user);
    }
}
