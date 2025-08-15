package com.intoThe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressDTO {
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

    public AddressDTO() {
        this("Y");
    }

    public AddressDTO(String isAddressActive) {
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
}
