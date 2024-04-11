package com.saleCampaignManagement.saleManagement.repositories;

import com.saleCampaignManagement.saleManagement.entities.Campaign;
import com.saleCampaignManagement.saleManagement.entities.CampaignDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface Campaign_repo extends JpaRepository<Campaign, Integer> {
    @Query("SELECT c FROM Campaign c WHERE c.s_date <= :startDate AND c.e_date > :startDate AND c.flag = 0")
    List<Campaign> findByStartDateAndFlag(@Param("startDate") LocalDate startDate);

    @Query("SELECT c FROM Campaign c WHERE c.e_date <= :endDate AND c.flag = 1")
    List<Campaign> findByEndDateAndFlag(@Param("endDate") LocalDate endDate);

    @Query("SELECT c FROM Campaign c WHERE c.s_date < :date")
    List<Campaign> findByS_dateBefore(@Param("date") LocalDate date);

    @Query("SELECT c FROM Campaign c WHERE c.s_date > :date")
    List<Campaign> findByS_dateAfter(@Param("date") LocalDate date);

    @Query("SELECT c FROM Campaign c WHERE c.s_date <= :date AND c.e_date >= :date")
    List<Campaign> findRunningCampaign(@Param("date") LocalDate now);
}
