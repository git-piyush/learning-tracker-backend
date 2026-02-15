package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.LoginRequest;
import com.piyush.InventoryManagementSystem.dto.RegisterRequest;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.dto.UserDTO;
import com.piyush.InventoryManagementSystem.entity.User;

public interface UserService {
    Response registerUser(RegisterRequest registerRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    User getCurrentLoggedInUser();
    Response updateUser(Long id, UserDTO userDTO);
    Response deleteUser(Long id);
}
