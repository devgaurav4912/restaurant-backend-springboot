package com.restaurant_backend.restaurant_backend.service.impl;

import com.restaurant_backend.restaurant_backend.entity.CategoryMaster;
import com.restaurant_backend.restaurant_backend.entity.ProductMaster;
import com.restaurant_backend.restaurant_backend.repository.CategoryRepository;
import com.restaurant_backend.restaurant_backend.repository.ProductRepository;
import com.restaurant_backend.restaurant_backend.service.CloudinaryImageService;
import com.restaurant_backend.restaurant_backend.service.ProductMasterService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CloudinaryImageService cloudinaryImageService;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<ProductMaster> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductMaster findById(Long productId) {

        Optional<ProductMaster> result = productRepository.findById(productId);

        ProductMaster product = null;

        if (result.isPresent()) {

            product = result.get();
        } else {

            throw new RuntimeException("Did not find Product Id - " + productId);
        }
        return product;
    }

    @Override
    public ProductMaster save(ProductMaster product, String categoryName, MultipartFile file) throws IOException {

        CategoryMaster category = categoryRepository.findCategoryNameByCategoryName(categoryName);
        product.setCategoryMaster(category);

        if (product.getCreatedOn() == null) {
            product.setCreatedOn(LocalDate.now());
        }
        Map<String, Object> uploadResult = cloudinaryImageService.upload(file);
        String imageUrl = (String) uploadResult.get("url");
        // Setting the image URL to the product
        product.setProductImage(imageUrl);

        return productRepository.save(product);
    }

    @Override
    public ProductMaster update(ProductMaster product, String categoryName, MultipartFile file) {
        Transaction transaction = null;
        try {

            Session session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            CategoryMaster category = categoryRepository.findCategoryNameByCategoryName(categoryName);
            product.setCategoryMaster(category);

            if (product.getCreatedOn() == null) {
                // Setting the current time to the product
                product.setCreatedOn(LocalDate.now());
            }
            Map<String, Object> uploadResult = cloudinaryImageService.upload(file);
            String imageUrl = (String) uploadResult.get("url");
            // Setting the image URL to the product
            product.setProductImage(imageUrl);

            session.update(product);

            transaction.commit();

        } catch (Exception e) {

        }

        return product;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductMaster findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public List<ProductMaster> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
}
