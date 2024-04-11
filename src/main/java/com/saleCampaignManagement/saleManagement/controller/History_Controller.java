package com.saleCampaignManagement.saleManagement.controller;

import com.saleCampaignManagement.saleManagement.entities.History;
import com.saleCampaignManagement.saleManagement.repositories.History_repo;
import com.saleCampaignManagement.saleManagement.services.History_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class History_Controller {
    @Autowired
    History_Service history_service;

    @GetMapping("/getHistory")
    public ResponseEntity<List<History>> getHistory() {
        try {
            List<History> histories = history_service.getHistory();
            return ResponseEntity.of(Optional.of(histories));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
