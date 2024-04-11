package com.saleCampaignManagement.saleManagement.repositories;

import com.saleCampaignManagement.saleManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Product_repo extends JpaRepository<Product, Integer> {
}
