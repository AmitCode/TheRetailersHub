package com.intoThe.service;

import com.intoThe.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<?> addUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO);
    public String deleteUser(Long userId);
    public String activateOrDeactivate(Long userId, String isActive);
    public UserDTO getUserInfo(Long UserId);
    public List<UserDTO> getAllUsers();
}
