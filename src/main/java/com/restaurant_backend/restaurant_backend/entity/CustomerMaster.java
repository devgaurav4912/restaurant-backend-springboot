package com.restaurant_backend.restaurant_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customermaser")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    @Column(name = "customer_name")
    private String customerFullName;

    @Column(name = "customer_mobile")
    private String customerMobileNumber;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    Set<CartMaster> cartMasters = new HashSet<>();
}
