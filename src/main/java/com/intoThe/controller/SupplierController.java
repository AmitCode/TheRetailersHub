package com.intoThe.controller;

import com.intoThe.dto.SupplierDTO;
import com.intoThe.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SupplierService") // 👈 This sets the base URL path
public class SupplierController {

    //@Autowired
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/getMessage")
    public String getMessage(){
        System.out.println("This is your message!....");
        return "This is your message";
    }

    @PostMapping("/addSuppliers")
    public String addSuppliers(@RequestBody SupplierDTO supplierDTO){

        return supplierService.addNewSuppliers(supplierDTO);
    }

    @PutMapping("/updateSupplierInfo")
    public String updateSupplierInfo(@RequestBody SupplierDTO supplierDTO){
        return supplierService.updateSupplierInfo(supplierDTO);
    }

    @PatchMapping("/activateOrDeactivateSuppliers")
    public ResponseEntity<String> activateOrDeactivateSuppliers(@RequestHeader int suppliersId,
                                                        @RequestHeader String activateStatus){
        return new ResponseEntity<>(supplierService.inActiveOrDeactivate(
                suppliersId,activateStatus), HttpStatus.OK);
    }

    @DeleteMapping("/removeSupplier")
    public String removeSupplier(@RequestHeader int supplierId){
        return supplierService.deleteSupplier(supplierId);
    }
}
