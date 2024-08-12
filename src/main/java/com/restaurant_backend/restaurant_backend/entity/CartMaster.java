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
@Table(name = "cartmaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartMaster {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "product_name")
    private  String productName;

    @Column(name = "product_price")
    private int price;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "product_quantity")
    private  int productQuantity;

    @Column(name = "subtotal")
    private double subTotal;

    @Column(name = "discount_percentage")
    private  double discountPercentage;

    @Column(name = "discountamount")
    private  double discountAmount;

    @Column(name = "net_bill")
    private  double  netBill;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "status")
    private  String status;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductMaster> products =new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerMaster customer;



}

