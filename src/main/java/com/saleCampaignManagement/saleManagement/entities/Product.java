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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    private String title;
    private String description;
    private double mrp;
    @OneToOne(cascade = CascadeType.ALL)
    private Discount discount = new Discount();
    private double current_price;

    public Product() {
        this.current_price = this.mrp;
    }
    @PrePersist
    public void set_current_price() {
        this.current_price = this.mrp;
    }

    public void addDiscount(double discount) {
        this.current_price = this.current_price - (discount * this.current_price) / 100;
    }

    public void removeDiscount(double amount) {
        this.current_price += amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", mrp=" + mrp +
                ", discount=" + discount.getDiscount() +
                ", current_price=" + current_price +
                '}';
    }
}
