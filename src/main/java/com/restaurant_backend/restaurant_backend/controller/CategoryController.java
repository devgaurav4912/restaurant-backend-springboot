package com.restaurant_backend.restaurant_backend.controller;

import com.restaurant_backend.restaurant_backend.entity.CategoryMaster;
import com.restaurant_backend.restaurant_backend.service.CategoryMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryMasterService categoryMasterService;


    @PostMapping("/create-category")
    ResponseEntity<CategoryMaster> saveCategory(@RequestPart("category") CategoryMaster category,
                                                 @RequestParam("file") MultipartFile file) {

        System.out.println("Received Category: " + category);
        System.out.println("Received File: " + file.getOriginalFilename());

        CategoryMaster categoryMaster = categoryMasterService.save(category,file);

        return ResponseEntity.ok(categoryMaster);

    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryMaster>> getAllCategory() {
        return ResponseEntity.ok(categoryMasterService.findAll());
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryMaster> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryMasterService.findById(categoryId));
    }

    @PutMapping("/updateCategory/{id}")
    public CategoryMaster updateCategory(@PathVariable("id") Long id,
                                         @RequestPart("category") CategoryMaster category,
                                         @RequestParam(value = "file",required = false) MultipartFile file) {
        category.setCategoryId(id);
//        if (file != null && !file.isEmpty()) {
//            return categoryMasterService.update(category, file);
//        } else {
            return categoryMasterService.update(category, file);


    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryMasterService.deleteById(id);
    }
}


