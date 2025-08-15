package com.intoThe.service.impl;

import com.intoThe.dto.AddressDTO;
import com.intoThe.entities.Address;
import com.intoThe.mapper.AddressModelMapper;
import com.intoThe.repository.AddressRepository;
import com.intoThe.service.AddressService;
import com.intoThe.utils.AddressUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressModelMapper addressModelMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressModelMapper addressModelMapper) {
        this.addressRepository = addressRepository;
        this.addressModelMapper = addressModelMapper;
    }

    @Override
    public String addNewAddress(AddressDTO addressDTO){
        Address address = addressRepository.save(addressModelMapper.
                mapToAddress(addressDTO));
        return "Address has been created!...";
    }

    @Override
    public AddressDTO updateExistingAddress(AddressDTO addressDTO) {

        Address exitingAddress = AddressUtils.isAddressExist(addressDTO.getAddressId(),addressRepository);

        exitingAddress.setAddressType(addressDTO.getAddressType());
        exitingAddress.setHome(addressDTO.getHome());
        exitingAddress.setArea(addressDTO.getArea());
        exitingAddress.setPinCode(addressDTO.getPinCode());
        exitingAddress.setCity(addressDTO.getCity());
        exitingAddress.setState(addressDTO.getState());
        exitingAddress.setCountry(addressDTO.getCountry());
        exitingAddress.setIsPrimaryAddress(addressDTO.getIsPrimaryAddress());
        exitingAddress.setIsAddressActive(addressDTO.getIsAddressActive());

        return addressModelMapper.mapToAddressDTO(exitingAddress);
    }

    @Override
    public String deleteExistingAddress(Long addressId) {
        addressRepository.deleteById(addressId);
        return "address has been deleted!...";
    }

    @Override
    public AddressDTO modifyActiveStatusOfAddress(Long addressId, String isActive) {
        Address address = AddressUtils.isAddressExist(addressId,addressRepository);
        address.setIsAddressActive("Y");
        Address newAddress = addressRepository.save(address);
        return addressModelMapper.mapToAddressDTO(newAddress);
    }

    @Override
    public ArrayList<AddressDTO> getAddressByUserId(Long userId) {
        List<Address> addressList = addressRepository.findByAddressUserId(userId);
        return addressModelMapper.mapToAddressDTOList(addressList);
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = AddressUtils.isAddressExist(addressId,addressRepository);
        return addressModelMapper.mapToAddressDTO(address);

    }

    @Override
    public ArrayList<AddressDTO> makeAddressAsPrimary(Long addressId) {
        Address address = AddressUtils.isAddressExist(addressId,addressRepository);
        address.setIsPrimaryAddress("Y");
        address = addressRepository.save(address);
        List<Address> addressList = addressRepository.findByAddressUserId(address.getAddressUserId());
        for (Address address1: addressList){
            if(!Objects.equals(address1.getAddressId(), address.getAddressId())){
                address1.setIsPrimaryAddress("N");
                addressRepository.save(address1);
            }
        }
        return addressModelMapper.mapToAddressDTOList(addressList);
    }
}
