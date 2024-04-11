package com.saleCampaignManagement.saleManagement.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaign_id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate s_date;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate e_date;
    private int flag;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "campaign_id")
    private List<CampaignDiscount> campaign_discount_list;
}
