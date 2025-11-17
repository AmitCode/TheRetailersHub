package com.intoThe.dto;

import com.intoThe.entities.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressDTO {
    private Long addressId;
    @NotBlank(message = "Address type can't be null.")
    private String addressType;
    @NotBlank(message = "Home can't be null.")
    private String home;
    @NotBlank(message = "Pin code can't be null.")
    private String pinCode;
    @NotBlank(message = "Area can't be null.")
    private String area;
    @NotBlank(message = "City can't be null.")
    private String city;
    @NotBlank(message = "State can't be null.")
    private String state;
    @NotBlank(message = "Country can't be null.")
    private String country;
    @NotBlank(message = "Please select primary address.")
    private String isPrimaryAddress;
    private String isAddressActive;
    private Users userInfo;

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

    public Users getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Users userInfo) {
        this.userInfo = userInfo;
    }
}
