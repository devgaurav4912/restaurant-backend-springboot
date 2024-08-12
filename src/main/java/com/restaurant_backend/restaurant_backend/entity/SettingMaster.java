package com.restaurant_backend.restaurant_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "setting_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_mobile")
    private String businessMobile;

    @Column(name = "business_email")
    private String businessEmail;

    @Column(name = "business_address")
    private String businessAddress;

    @Column(name = "business_Gst_number")
    private String businessGstNumber;

    @Column(name = "business_logo")
    private String businessLogo;
}
