package com.intoThe.service;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<?> addUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO);
    public ResponseEntity<AuthenticationServiceResponse> deleteUser(String userName);
    public ResponseEntity<AuthenticationServiceResponse> activateOrDeactivate(String userName, Boolean isActive);
    public UserDTO getUserInfo(String userName);
    public List<UserDTO> getAllUsers();
}
