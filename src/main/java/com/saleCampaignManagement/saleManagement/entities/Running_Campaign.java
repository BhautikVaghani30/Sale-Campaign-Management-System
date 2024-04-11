package com.saleCampaignManagement.saleManagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table
@Data
@DynamicInsert
@DynamicUpdate
public class Running_Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int running_campaign_id;
    private int campaign_id;
    private LocalDate end_date;
    private int product_id;
    private double discount;
    private double discount_amount;

    public Running_Campaign() {
    }

    public Running_Campaign(int campaign_id, LocalDate eDate, int productId, double discount, double amount) {
        this.end_date = eDate;
        this.product_id = productId;
        this.discount = discount;
        this.discount_amount = amount;
        this.campaign_id = campaign_id;
    }
}
