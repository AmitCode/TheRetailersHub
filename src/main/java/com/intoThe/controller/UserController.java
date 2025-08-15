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

    /**
     * This endpoint is used to create a new user in the system.
     *
     * @param userDTO The user data to be created.
     * @return A string indicating that the user was successfully created.
     */
    @PostMapping("/createNewUser")
    public String createNewUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    /**
     * This endpoint is used to update an existing user in the system.
     *
     * @param userDTO The updated user data.
     * @return The updated user data wrapped in a {@link ResponseEntity} object
     *         with an HTTP status of {@link HttpStatus#ACCEPTED}.
     */
    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.ACCEPTED);
    }

    /**
     * This endpoint is used to retrieve the user information for a specified user ID.
     *
     * @param userId The ID of the user to retrieve information for.
     * @return The user information wrapped in a {@link ResponseEntity} object, with an HTTP status of {@link HttpStatus#FOUND}.
     */
    @GetMapping("/getUserInfoById")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader Long userId){
        return new ResponseEntity<>(userService.getUserInfo(userId),HttpStatus.FOUND);
    }

    /**
     * This endpoint is used to retrieve information for all users in the system.
     *
     * @return A list of user information wrapped in a {@link ResponseEntity} object,
     *         with an HTTP status of {@link HttpStatus#FOUND}.
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);
    }

    /**
     * This endpoint is used to activate or deactivate a user in the system.
     *
     * @param userId The ID of the user to be activated or deactivated.
     * @param isActive A string that is case-insensitive and can only be "Y" for activation or "N" for deactivation.
     * @return A {@link ResponseEntity} object containing a string indicating that the user has been successfully activated or deactivated,
     *         with an HTTP status of {@link HttpStatus#ACCEPTED}.
     * @throws IllegalArgumentException if the isActive parameter is neither "Y" nor "N".
     */
    @PatchMapping("/activateOrDeactivateUser")
    public ResponseEntity<String> modifyUserStatus(@RequestHeader Long userId,
                                                   @RequestHeader String isActive){
        return new ResponseEntity<>(userService.
                activateOrDeactivate(userId,isActive),HttpStatus.ACCEPTED);
    }
}
