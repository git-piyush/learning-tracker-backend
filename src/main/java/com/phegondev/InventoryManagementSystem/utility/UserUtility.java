package com.phegondev.InventoryManagementSystem.utility;

import com.phegondev.InventoryManagementSystem.entity.User;
import com.phegondev.InventoryManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserUtility {

    @Autowired
    private UserRepository userRepository;

    public User getLoggedInUser(){
        String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userName = authentication.getName();
        }
        User user = userRepository.findByEmail(userName).get();

        return user;
    }

}
