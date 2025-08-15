package com.intoThe.mapper;

import com.intoThe.dto.SupplierDTO;
import com.intoThe.entities.Suppliers;

public class SuppliersModelMapper {

    public SupplierDTO transferEntityToDTO(Suppliers supplierInfo){
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSuppliersId(supplierInfo.getSuppliersId());
        supplierDTO.setSupplierName(supplierInfo.getSupplierName());
        supplierDTO.setSupplierEmail(supplierInfo.getSupplierEmail());
        supplierDTO.setSupplierContactNumber(supplierInfo.getSupplierContactNumber());
        supplierDTO.setSupplierAddress(supplierInfo.getSupplierAddress());
        supplierDTO.setSupplierType(supplierInfo.getSupplierType());
        supplierDTO.setIsActive(supplierInfo.getIsActive());
        return supplierDTO;
    }

    public static Suppliers transferDtoToEntity(SupplierDTO supplierDTO){
        Suppliers suppliers = new Suppliers();
        suppliers.setSupplierName(supplierDTO.getSupplierName());
        suppliers.setSupplierContactNumber(supplierDTO.getSupplierContactNumber());
        suppliers.setSupplierEmail(supplierDTO.getSupplierEmail());
        suppliers.setSupplierAddress(supplierDTO.getSupplierAddress());
        suppliers.setSupplierType(supplierDTO.getSupplierType());
        return suppliers;
    }
}
