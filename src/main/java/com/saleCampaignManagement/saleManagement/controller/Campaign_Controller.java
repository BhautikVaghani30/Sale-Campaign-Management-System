package com.saleCampaignManagement.saleManagement.controller;

import com.saleCampaignManagement.saleManagement.entities.Campaign;
import com.saleCampaignManagement.saleManagement.entities.CampaignDiscount;
import com.saleCampaignManagement.saleManagement.entities.Product;
import com.saleCampaignManagement.saleManagement.services.Campaign_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campaign")
public class Campaign_Controller {

    @Autowired
    Campaign_Service campaign_service;

    @PostMapping("/addAllCampaign")
    public ResponseEntity<String> addAllCampaign(@RequestBody List<Campaign> list) {
        try{
            campaign_service.addAll(list);
            return ResponseEntity.status(HttpStatus.CREATED).body("successfully added!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getPast")
    public ResponseEntity<List<Campaign>> getPast() {
        try {
            List<Campaign> list = campaign_service.getPastCampaign();
            return ResponseEntity.of(Optional.of(list));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getUpcoming")
    public ResponseEntity<List<Campaign>> getUpcoming() {
        try {
            List<Campaign> list = campaign_service.getUpcomingCampaign();
            return ResponseEntity.of(Optional.of(list));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getRunning")
    public ResponseEntity<List<Campaign>> getRunning() {
        try {
            List<Campaign> list = campaign_service.getRunningCampaign();
            return ResponseEntity.of(Optional.of(list));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            campaign_service.deleteAll();
            return ResponseEntity.ok().body("Successfully deleted all campaigns!");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        try {
            campaign_service.deleteById(id);
            return ResponseEntity.ok().body("Successfully deleted campaign with id : " + id);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
