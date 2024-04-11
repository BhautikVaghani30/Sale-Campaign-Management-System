package com.saleCampaignManagement.saleManagement.services;

import com.saleCampaignManagement.saleManagement.entities.Product;
import com.saleCampaignManagement.saleManagement.repositories.Product_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;
import java.util.List;
@Service
public class Product_Services {
    @Autowired
    Product_repo product_repo;

    public List<Product> getAll(Pageable p) {
        Page<Product> list = product_repo.findAll(p);
        return (List<Product>) list.getContent();
    }

    public Product getById(int id) {
        return product_repo.findById(id).get();
    }

    public void addAll(List<Product> list) {
        product_repo.saveAll(list);
    }

    public void deleteAll() {
        product_repo.deleteAll();
    }
}
