package com.intoThe.controller;

import com.intoThe.dto.UserDTO;
import com.intoThe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserService")
public class UserController {
    @Autowired
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/createNewUser")
    public String createNewUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUserInfoById")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader Long userId){
        return new ResponseEntity<>(userService.getUserInfo(userId),HttpStatus.FOUND);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);
    }

    @PatchMapping("/activateOrDeactivateUser")
    public ResponseEntity<String> modifyUserStatus(@RequestHeader Long userId,
                                                   @RequestHeader String isActive){
        return new ResponseEntity<>(userService.
                activateOrDeactivate(userId,isActive),HttpStatus.ACCEPTED);
    }
}
