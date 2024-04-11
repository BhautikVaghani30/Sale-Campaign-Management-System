package com.saleCampaignManagement.saleManagement.repositories;

import com.saleCampaignManagement.saleManagement.entities.Campaign;
import com.saleCampaignManagement.saleManagement.entities.Running_Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface Running_Campaign_repo extends JpaRepository<Running_Campaign, Integer> {
    @Query("SELECT c FROM Running_Campaign c WHERE c.campaign_id = :id")
    List<Running_Campaign> findByCampaign_id(@Param("id") int campaign_id);
}
