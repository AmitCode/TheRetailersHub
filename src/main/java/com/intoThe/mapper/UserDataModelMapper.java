package com.intoThe.mapper;

import com.intoThe.dto.AddressDTO;
import com.intoThe.dto.UserDTO;
import com.intoThe.entities.Address;
import com.intoThe.entities.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDataModelMapper {

    /**
     * This method maps a {@link UserDTO} object to a {@link Users} entity object.
     *
     * @param userDTO The {@link UserDTO} object to be mapped.
     * @return The mapped {@link Users} entity object.
     */
    public Users mapToUser(UserDTO userDTO){

        Users users = new Users();
        users.setUserFirstName(userDTO.getUserFirstName());
        users.setUserMiddleName(userDTO.getUserMiddleName());
        users.setUserLastName(userDTO.getUserLastName());
        users.setUserContactNumber(userDTO.getUserContactNumber());
        users.setUserEmailId(userDTO.getUserEmailId());
        users.setIsEmailVarified(userDTO.getIsEmailVarified());
        users.setIsMobileVarified(userDTO.getIsMobileVarified());
        users.setPassword(userDTO.getUserPassword());
        users.setConfirmPassword(userDTO.getIsPasswordVarified());

        List<Address> addresses = userDTO.getAddresses().stream()
                //.map(addressDTO -> AddressModelMapper.mapToAddress(addressDTO))writing it to method reference
                .map(AddressModelMapper::mapToAddress)//Method reference
                .collect(Collectors.toList());

        addresses.forEach(address -> address.setUserInfo(users));
        users.setAddresses(addresses);

        return users;
    }

    /**
     * This method maps a {@link Users} entity object to a {@link UserDTO} object.
     *
     * @param users The {@link Users} entity object to be mapped.
     * @return The mapped {@link UserDTO} object.
     */
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
        userDTO.setUserPassword(users.getPassword());
        userDTO.setIsPasswordVarified(users.getConfirmPassword());

        List<AddressDTO> addresses = users.getAddresses().stream()
                .map(AddressModelMapper::mapToAddressDTO)
                .collect(Collectors.toList());

        //addresses.forEach(address -> address.setUserInfo(users));

        userDTO.setAddresses(addresses);

        return userDTO;
    }

    /**
     * This method maps a list of {@link Users} entity objects to a list of {@link UserDTO} objects.
     *
     * @param users The list of {@link Users} entity objects to be mapped.
     * @return The mapped list of {@link UserDTO} objects.
     */
    public ArrayList<UserDTO> mapToListOfUserDTO(List<Users> users){
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (Users user : users){
            userDTOS.add(mapToUserDTO(user));
        }
        return userDTOS;
    }
}
