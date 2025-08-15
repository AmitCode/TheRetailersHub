package com.intoThe.service.impl;

import com.intoThe.dto.UserDTO;
import com.intoThe.entities.Users;
import com.intoThe.mapper.UserDataModelMapper;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.UserService;
import com.intoThe.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    private final UserDataModelMapper userModelMapper = new UserDataModelMapper();


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String addUser(UserDTO userDTO) {
        try{
            Users newUser = userRepository.save(userModelMapper.mapToUser(userDTO));
        }catch (Exception ex){
            System.out.println("{UserServiceImpl} - [addUser] - Ex-" +ex.getMessage());
        }
        return "Data has been saved successfully";
    }
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

    @Override
    public String deleteUser(Long userId) {
        Users users = UserUtils.isUserExist(userId,userRepository);
        userRepository.deleteById(userId);
        return "User has been deleted!...";
    }

    @Override
    public String activateOrDeactivate(Long userId, String isActive) {
        Users users = UserUtils.isUserExist(userId,userRepository);
        users.setIsUserActive(isActive);
        users = userRepository.save(users);
        if (isActive.equalsIgnoreCase("Y"))
            return "User has been Activated!...";
        return "User has been Deactivated!...";
    }

    @Override
    public UserDTO getUserInfo(Long userId) {
        return userModelMapper.mapToUserDTO(UserUtils.isUserExist(userId,userRepository));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        return userModelMapper.mapToListOfUserDTO(usersList);
    }
}
