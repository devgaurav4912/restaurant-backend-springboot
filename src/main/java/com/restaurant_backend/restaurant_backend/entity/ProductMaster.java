package com.restaurant_backend.restaurant_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productmaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private String productPrice;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "created_on")
    private LocalDate createdOn;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
//    CategoryMaster categoryMaster;


    @ManyToMany(mappedBy = "products" )
    private Set<CartMaster> carts = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryMaster categoryMaster;

}
