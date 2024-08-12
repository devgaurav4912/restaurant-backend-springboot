package com.restaurant_backend.restaurant_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "categorymaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_image")
    private String categoryImage;

    @JsonIgnore
    @Column(name = "created_on")
    private LocalDate createdOn;


//    @OneToMany(mappedBy = "categoryMaster")
//    private List<ProductMaster> productList ;

    @OneToMany(mappedBy = "categoryMaster" , cascade =  CascadeType.ALL)
    private List<ProductMaster> productsList;

}
