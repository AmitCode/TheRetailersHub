package com.intoThe.utils;

import com.intoThe.entities.Address;
import com.intoThe.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class AddressUtils {
    public static Address isAddressExist(Long addressId, AddressRepository addressRepository){
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new NoSuchElementException("Address Not Found"));

    }
}
