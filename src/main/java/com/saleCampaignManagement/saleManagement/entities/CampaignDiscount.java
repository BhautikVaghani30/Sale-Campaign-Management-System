package com.saleCampaignManagement.saleManagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table
@Data
@DynamicUpdate
@DynamicInsert
public class CampaignDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaign_discount_id;

    private int product_id;
    private double discount = 0.0;
}
