package com.saleCampaignManagement.saleManagement.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table
@Data
@DynamicUpdate
@DynamicInsert
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int history_id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    private int product_id;
    private double price;

    public History(LocalDate today, int productId, double currentPrice) {
        this.date = today;
        this.product_id = productId;
        this.price = currentPrice;
    }
}
