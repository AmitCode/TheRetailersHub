package com.intoThe.mapper;

import com.intoThe.dto.UserDTO;
import com.intoThe.entities.Users;

import java.util.ArrayList;
import java.util.List;

public class UserDataModelMapper {

    public Users mapToUser(UserDTO userDTO){

        Users users = new Users();
        users.setUserFirstName(userDTO.getUserFirstName());
        users.setUserMiddleName(userDTO.getUserMiddleName());
        users.setUserLastName(userDTO.getUserLastName());
        users.setUserContactNumber(userDTO.getUserContactNumber());
        users.setUserEmailId(userDTO.getUserEmailId());
        users.setIsEmailVarified(userDTO.getIsEmailVarified());
        users.setIsMobileVarified(userDTO.getIsMobileVarified());

        return users;
    }

    public UserDTO mapToUserDTO(Users users){

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(users.getUserId());
        userDTO.setUserFirstName(users.getUserFirstName());
        userDTO.setUserMiddleName(users.getUserMiddleName());
        userDTO.setUserLastName(users.getUserLastName());
        userDTO.setIsUserActive(users.getIsUserActive());
        userDTO.setUserEmailId(users.getUserEmailId());
        userDTO.setUserContactNumber(users.getUserContactNumber());
        userDTO.setIsMobileVarified(users.getIsMobileVarified());
        userDTO.setIsEmailVarified(users.getIsEmailVarified());

        return userDTO;
    }

    public ArrayList<UserDTO> mapToListOfUserDTO(List<Users> users){
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (Users user : users){
            userDTOS.add(mapToUserDTO(user));
        }
        return userDTOS;
    }
}
