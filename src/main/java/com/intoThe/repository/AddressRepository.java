package com.intoThe.repository;

import com.intoThe.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    public List<Address> findByAddressUserId(Long addressUserId);
}
