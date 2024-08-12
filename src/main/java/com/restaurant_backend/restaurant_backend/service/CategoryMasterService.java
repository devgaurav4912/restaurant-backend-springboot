package com.restaurant_backend.restaurant_backend.service;

import com.restaurant_backend.restaurant_backend.entity.CategoryMaster;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryMasterService {
    List<CategoryMaster> findAll();

    CategoryMaster findById(Long categoryId);

    CategoryMaster save(CategoryMaster category , MultipartFile file);

    CategoryMaster update(CategoryMaster category,MultipartFile file);

    void deleteById(Long id);
}
