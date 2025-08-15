package com.intoThe.service.impl;

import com.intoThe.dto.SupplierDTO;
import com.intoThe.entities.Suppliers;
import com.intoThe.mapper.SuppliersModelMapper;
import com.intoThe.repository.SuppliersRepository;
import com.intoThe.service.SupplierService;
import com.intoThe.utils.SuppliersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {


    private final SuppliersRepository suppliersRepository;
    private final SuppliersUtils suppliersUtils;

    @Autowired
    public SupplierServiceImpl(SuppliersRepository suppliersRepository,SuppliersUtils suppliersUtils) {
        this.suppliersRepository = suppliersRepository;
        this.suppliersUtils = suppliersUtils;
    }

    public String addNewSuppliers(SupplierDTO supplierDTO){
        Suppliers suppliers = new Suppliers();
        suppliers = SuppliersModelMapper.transferDtoToEntity(supplierDTO);
        suppliersRepository.save(suppliers);
        return "saved";
    }

    public String updateSupplierInfo(SupplierDTO supplierDTO){
        Suppliers suppliers = suppliersUtils.isSupplierExist(supplierDTO.getSuppliersId());

        suppliersRepository.save(updateDetails(suppliers,supplierDTO));

        return "Supplier details has been updated";
    }

    public Suppliers updateDetails(Suppliers suppliers,SupplierDTO supplierDTO){
        suppliers.setSupplierName(supplierDTO.getSupplierName());
        suppliers.setSupplierContactNumber(supplierDTO.getSupplierContactNumber());
        suppliers.setSupplierEmail(supplierDTO.getSupplierEmail());
        suppliers.setSupplierAddress(supplierDTO.getSupplierAddress());
        suppliers.setSupplierType(supplierDTO.getSupplierType());
        suppliers.setIsActive(supplierDTO.getIsActive());
        return suppliers;

    }


    public String inActiveOrDeactivate(int supplierId, String activeStatus) {
        Suppliers suppliers = suppliersUtils.isSupplierExist(supplierId);

        suppliers.setIsActive(activeStatus.toUpperCase());
        suppliersRepository.save(suppliers);
        return "Suppliers deactivated.";
    }

    public String deleteSupplier(int supplierId) {
        Suppliers suppliers = suppliersUtils.isSupplierExist(supplierId);
        suppliersRepository.deleteById(supplierId);
        return "Supplier info has been removed!...";
    }
}
