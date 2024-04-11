package com.saleCampaignManagement.saleManagement.controller;

import com.saleCampaignManagement.saleManagement.entities.Product;
import com.saleCampaignManagement.saleManagement.services.Product_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/product")
public class Product_Controller {
    @Autowired
    Product_Services product_services;

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAll(@RequestParam(value = "pageNumber", defaultValue = "0",
    required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10",
            required = false) int pageSize) {
        try{
            Pageable p = PageRequest.of(pageNumber, pageSize);
            List<Product> list = product_services.getAll(p);

            if(list.size() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.of(Optional.of(list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id) {
        try{
            Product item = product_services.getById(id);
            if(item == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.of(Optional.of(item));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addAll")
    public ResponseEntity<Void> getById(@RequestBody List<Product> list) {
        try{
            product_services.addAll(list);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> getById() {
        try{
            product_services.deleteAll();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
