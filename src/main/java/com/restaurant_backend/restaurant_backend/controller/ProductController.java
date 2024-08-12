package com.restaurant_backend.restaurant_backend.controller;

import com.restaurant_backend.restaurant_backend.entity.ProductMaster;
import com.restaurant_backend.restaurant_backend.service.ProductMasterService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductMasterService productMasterService;


    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductMaster>> getAllProducts() {
        return ResponseEntity.ok(productMasterService.findAll());
    }

    @GetMapping("/category/{categoryName}")
    public List<ProductMaster> getProductsByCategoryName(@PathVariable String categoryName) {
        return productMasterService.getProductsByCategoryName(categoryName);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductMaster> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productMasterService.findById(productId));
    }

    @PostMapping("/add-product/{categoryName}")
    ResponseEntity<ProductMaster> saveProduct(@RequestPart("product") ProductMaster product,
                                              @RequestParam("file") MultipartFile file,
                                              @PathVariable("categoryName") String categoryName
    ) {
        try {
            ProductMaster savedProduct = productMasterService.save(product, categoryName, file);
            return ResponseEntity.ok(savedProduct);

        } catch (IOException e) {

            return ResponseEntity.status(500).body(null);
        }
    }


    @PutMapping("/updateProduct/{categoryName}")
    public ResponseEntity<ProductMaster> updateProduct(@RequestPart("product") ProductMaster product,
                                                       @RequestParam("file") MultipartFile file,
                                                       @PathVariable String categoryName
    ) {
        ProductMaster updateProduct = null;
        try {
            if (product.getProductId() == null) {
                // Fetch the product by name
                ProductMaster existingProduct = productMasterService.findByProductName(product.getProductName());
                if (existingProduct == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Product not found
                }
                product.setProductId(existingProduct.getProductId());
            }            updateProduct = productMasterService.update(product, categoryName, file);
            return ResponseEntity.ok(updateProduct);

        } catch (IOException e) {

            return ResponseEntity.status(500).body(null);

        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productMasterService.deleteById(id);
    }


}
