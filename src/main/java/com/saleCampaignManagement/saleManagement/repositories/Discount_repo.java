package com.saleCampaignManagement.saleManagement.repositories;

import com.saleCampaignManagement.saleManagement.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Discount_repo extends JpaRepository<Discount, Integer> {
}
