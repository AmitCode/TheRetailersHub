package com.intoThe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "INTO_ADDRESS_DETAILS")
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private Long addressUserId;
    private String addressType;
    private String home;
    private String pinCode;
    private String area;
    private String city;
    private String state;
    private String country;
    private String isPrimaryAddress;
    private String isAddressActive;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdatedAt;

    public Address() {
        this("Y");
    }

    public Address(String isAddressActive) {
        this.isAddressActive = isAddressActive;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getAddressUserId() {
        return addressUserId;
    }

    public void setAddressUserId(Long addressUserId) {
        this.addressUserId = addressUserId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsPrimaryAddress() {
        return isPrimaryAddress;
    }

    public void setIsPrimaryAddress(String isPrimaryAddress) {
        this.isPrimaryAddress = isPrimaryAddress;
    }

    public String getIsAddressActive() {
        return isAddressActive;
    }

    public void setIsAddressActive(String isAddressActive) {
        this.isAddressActive = isAddressActive;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
