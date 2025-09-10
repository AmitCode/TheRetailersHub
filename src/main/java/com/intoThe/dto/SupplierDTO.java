package com.intoThe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private int suppliersId;
    @NotBlank(message = "Supplier name can't be null.")
    private String supplierName;
    @NotBlank(message = "Supplier email can't be null.")
    @Email(message = "Email must be in proper format.")
    private String supplierEmail;
    @NotBlank(message = "Supplier contact number can't be null.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    private String supplierContactNumber;
    private String isActive;
    private String supplierType;
    private String supplierAddress;

    public SupplierDTO() {
        this("Y");
    }

    public SupplierDTO(String isActive) {
        System.out.println("Constructor chaining!....");
        this.isActive = isActive;
    }

    public int getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(int suppliersId) {
        this.suppliersId = suppliersId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierContactNumber() {
        return supplierContactNumber;
    }

    public void setSupplierContactNumber(String supplierContactNumber) {
        this.supplierContactNumber = supplierContactNumber;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
}
