package com.saleCampaignManagement.saleManagement.repositories;

import com.saleCampaignManagement.saleManagement.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface History_repo extends JpaRepository<History, Integer> {
}
