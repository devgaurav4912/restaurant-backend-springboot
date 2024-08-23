package com.restaurant_backend.restaurant_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
    private double productPrice;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "product_quantity")
    private double productQuantity;

    @Column(name = "product_total")
    private double productTotal;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
//    CategoryMaster categoryMaster;


//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    private CartMaster carts;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private Set<CartMaster> carts = new HashSet<>();


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryMaster categoryMaster;

    @JsonProperty("categoryName")//categoryName
    public String getCategoryName() {
        return categoryMaster != null ? categoryMaster.getCategoryName() : null;
    }

}
