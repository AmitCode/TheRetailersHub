package com.intoThe.mapper;

import com.intoThe.dto.AddressDTO;
import com.intoThe.entities.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressModelMapper {
    public AddressDTO mapToAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setAddressUserId(address.getAddressUserId());
        addressDTO.setAddressType(address.getAddressType());
        addressDTO.setHome(address.getHome());
        addressDTO.setArea(address.getArea());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setIsPrimaryAddress(address.getIsPrimaryAddress());
        addressDTO.setIsAddressActive(address.getIsAddressActive());
        return addressDTO;
    }

    public Address mapToAddress(AddressDTO addressDTO){
        Address address = new Address();
        address.setAddressId(addressDTO.getAddressId());
        address.setAddressUserId(addressDTO.getAddressUserId());
        address.setAddressType(addressDTO.getAddressType());
        address.setHome(addressDTO.getHome());
        address.setArea(addressDTO.getArea());
        address.setPinCode(addressDTO.getPinCode());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setIsPrimaryAddress(addressDTO.getIsPrimaryAddress());
        address.setIsAddressActive(addressDTO.getIsAddressActive());
        return address;
    }


    public ArrayList<AddressDTO> mapToAddressDTOList(List<Address> addressList){
        ArrayList<AddressDTO> addressDTOList = new ArrayList<>();
        for(Address address : addressList){
            addressDTOList.add(mapToAddressDTO(address));
        }
        return addressDTOList;
    }
}
