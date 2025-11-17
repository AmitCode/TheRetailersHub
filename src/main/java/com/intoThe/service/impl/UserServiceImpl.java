package com.intoThe.service.impl;

import com.intoThe.dto.UserDTO;
import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.EmailIdAlreadyExist;
import com.intoThe.mapper.UserDataModelMapper;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.UserService;
import com.intoThe.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDataModelMapper userModelMapper = new UserDataModelMapper();


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * This method is used to add a new user to the database.
     *
     * @param userDTO The {@link UserDTO} object containing the details of the user to be added.
     * @return A string indicating that the data has been saved successfully.
     */
    @Override
    public String addUser(UserDTO userDTO) {
        String message = "";
        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        userDTO.setIsPasswordVarified(passwordEncoder.encode(userDTO.getIsPasswordVarified()));

            if(UserUtils.isUserExistWithEmail(userDTO.getUserEmailId(),userRepository)){
                throw new EmailIdAlreadyExist("User with this email id already exist!...");
            }else{
                Users newUser = userRepository.save(userModelMapper.mapToUser(userDTO));
                message = "Data has been saved successfully";
            }

        return message;
    }
    /**
     * This method is used to update an existing user in the database.
     *
     * @param userDTO The {@link UserDTO} object containing the details of the user to be updated.
     * @return The updated {@link UserDTO} object.
     */
    @Override
    public UserDTO updateUser(UserDTO userDTO){

        Long userId = userDTO.getUserId();
        Users users = UserUtils.isUserExist(userId,userRepository);

        users.setUserFirstName(userDTO.getUserFirstName());
        users.setUserMiddleName(userDTO.getUserMiddleName());
        users.setUserLastName(userDTO.getUserLastName());
        users.setUserContactNumber(userDTO.getUserContactNumber());
        users.setUserEmailId(userDTO.getUserEmailId());
        users.setIsUserActive(userDTO.getIsUserActive());
        users.setIsEmailVarified(userDTO.getIsEmailVarified());
        users.setIsMobileVarified(userDTO.getIsMobileVarified());

        users = userRepository.save(users);

        return userModelMapper.mapToUserDTO(users);
    }

    /**
     * This method is used to delete an existing user from the database.
     *
     * @param userId The ID of the user to be deleted.
     * @return A string indicating that the user has been successfully deleted.
     */
    @Override
    public String deleteUser(Long userId) {
        Users users = UserUtils.isUserExist(userId,userRepository);
        userRepository.deleteById(userId);
        return "User has been deleted!...";
    }

    /**
     * This method is used to activate or deactivate a user in the database.
     *
     * @param userId The ID of the user to be activated or deactivated.
     * @param isActive A string that is case-insensitive and can only be "Y" for activation or "N" for deactivation.
     * @return A string indicating that the user has been successfully activated or deactivated.
     * @throws IllegalArgumentException if the isActive parameter is neither "Y" nor "N".
     */
    @Override
    public String activateOrDeactivate(Long userId, String isActive) {
        Users users = UserUtils.isUserExist(userId,userRepository);
        users.setIsUserActive(isActive);
        users = userRepository.save(users);
        if (isActive.equalsIgnoreCase("Y"))
            return "User has been Activated!...";
        return "User has been Deactivated!...";
    }

    /**
     * This method is used to get the user information for a given userId.
     *
     * @param userId The userId of the user to get the information for.
    /**
     *

     * @return A list of {@link UserDTO} objects representing all users in the database.

     * @return A {@link UserDTO} object containing the information of the user.
     */
    @Override
    public UserDTO getUserInfo(Long userId) {
        return userModelMapper.mapToUserDTO(UserUtils.isUserExist(userId,userRepository));
    }

    /**
     * This method is used to get the user information for all users in the database.
     *
     * @return A list of {@link UserDTO} objects representing all users in the database.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        return userModelMapper.mapToListOfUserDTO(usersList);
    }
}
