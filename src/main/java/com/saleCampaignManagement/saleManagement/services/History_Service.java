package com.saleCampaignManagement.saleManagement.services;

import com.saleCampaignManagement.saleManagement.entities.History;
import com.saleCampaignManagement.saleManagement.repositories.History_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class History_Service {
    @Autowired
    History_repo history_repo;

    public List<History> getHistory() {
        return history_repo.findAll();
    }
}
