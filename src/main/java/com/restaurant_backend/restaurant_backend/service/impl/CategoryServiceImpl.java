package com.restaurant_backend.restaurant_backend.service.impl;

import com.restaurant_backend.restaurant_backend.entity.CategoryMaster;
import com.restaurant_backend.restaurant_backend.repository.CategoryRepository;
import com.restaurant_backend.restaurant_backend.service.CategoryMasterService;
import com.restaurant_backend.restaurant_backend.service.CloudinaryImageService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryMasterService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private CloudinaryImageService cloudinaryImageService;

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<CategoryMaster> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryMaster findById(Long categoryId) {
        Optional<CategoryMaster> result = categoryRepository.findById(categoryId);

        CategoryMaster category = null;

        if (result.isPresent()) {

            category = result.get();
        } else {

            throw new RuntimeException("Did not find Product Id - " + categoryId);
        }
        return category;
    }

    @Override
    public CategoryMaster save(CategoryMaster category , MultipartFile file) {

        // Set the current date if createdOn is null

        if(category.getCreatedOn() == null){
            category.setCreatedOn(LocalDate.now());
        }

        Map<String, Object> uploadResult =cloudinaryImageService.upload(file);
        String imageUrl = (String) uploadResult.get("url");
        category.setCategoryImage(imageUrl);
        return categoryRepository.save(category);
    }

//    @Override
//    public CategoryMaster update(CategoryMaster categoryMaster , MultipartFile file) {
//        Transaction transaction = null;
//        try {
//
//            Session session = sessionFactory.openSession();
//
//            transaction = session.beginTransaction();
//
//            Map<String, Object> uploadResult = cloudinaryImageService.upload(file);
//            String imageUrl = (String) uploadResult.get("url");
//            // Set the image URL to the product
//            categoryMaster.setCategoryImage(imageUrl);
//
//            session.saveOrUpdate(categoryMaster);
//
//            transaction.commit();
//
//        } catch (Exception e) {
//
//        }
//
//        return categoryMaster;
//    }

    @Override
    public CategoryMaster update(CategoryMaster newCategory, MultipartFile file) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Fetch the existing category from the database
            CategoryMaster existingCategory = session.get(CategoryMaster.class, newCategory.getCategoryId());
            if (existingCategory == null) {
                throw new RuntimeException("Category not found");
            }

            // Update category name if provided
            if (newCategory.getCategoryName() != null) {
                existingCategory.setCategoryName(newCategory.getCategoryName());
            }

            // Update file if provided
            if (file != null && !file.isEmpty()) {
                Map<String, Object> uploadResult = cloudinaryImageService.upload(file);
                String imageUrl = (String) uploadResult.get("url");
                existingCategory.setCategoryImage(imageUrl);
            }

            // Save or update the existing category
            session.saveOrUpdate(existingCategory);
            transaction.commit();

            return existingCategory;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error updating category", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
