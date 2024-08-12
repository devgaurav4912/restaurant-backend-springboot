package com.restaurant_backend.restaurant_backend.controller;

import com.restaurant_backend.restaurant_backend.entity.CategoryMaster;
import com.restaurant_backend.restaurant_backend.entity.CustomerMaster;
import com.restaurant_backend.restaurant_backend.entity.ProductMaster;
import com.restaurant_backend.restaurant_backend.service.CustomerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerMasterService customerMasterService;

    @PostMapping("/addCustomer")
    ResponseEntity<CustomerMaster> saveCustomer(@RequestBody CustomerMaster customerMaster) {

        if (customerMaster.getCreatedOn() == null) {
            customerMaster.setCreatedOn(LocalDate.now());
        }
        CustomerMaster customerMaster1 = customerMasterService.save(customerMaster);

        return ResponseEntity.ok(customerMaster1);

    }

    @PutMapping("update/{customerId}")
    CustomerMaster updateCustomer(@RequestBody CustomerMaster customerMaster,
                                                  @PathVariable Long customerId) {
        if (customerMaster.getCreatedOn() == null) {
            customerMaster.setCreatedOn(LocalDate.now());
        }

        customerMaster.setCustomer_id(customerId);

        return  customerMasterService.save(customerMaster);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerMaster> getCategoryById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerMasterService.findById(customerId));
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<CustomerMaster>> getAllProducts() {
        return ResponseEntity.ok(customerMasterService.findAll());
    }

}
