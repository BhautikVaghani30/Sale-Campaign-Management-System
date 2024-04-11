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
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    protected double discount;

    public Discount() {
        this.discount = 0.0;
    }

    public void addDiscount(double discount) {
        this.discount += discount;
        if(this.discount > 100) this.discount = 100;
    }

    public void removeDiscount(double discount) {
        this.discount -= discount;
        if(this.discount < 0) this.discount = 0;
    }
}
