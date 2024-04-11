package com.saleCampaignManagement.saleManagement.services;

import com.saleCampaignManagement.saleManagement.entities.*;
import com.saleCampaignManagement.saleManagement.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class Campaign_Service {
    @Autowired
    Campaign_repo campaign_repo;

    @Autowired
    Discount_repo discount_repo;

    @Autowired
    Product_repo product_repo;

    @Autowired
    History_repo history_repo;

    @Autowired
    Running_Campaign_repo running_campaign_repo;

    public void addAll(List<Campaign> list) {
        campaign_repo.saveAll(list);
    }

    public void add(Campaign campaign) {
        campaign_repo.save(campaign);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Indians/Maldives")
    @Transactional
    public void applyDiscount() {
        try {
            LocalDate today = LocalDate.now();

            List<Campaign> apply = campaign_repo.findByStartDateAndFlag(today);
            List<Campaign> remove = campaign_repo.findByEndDateAndFlag(today);

            //apply the campaign
            for(Campaign c : apply) {
                    List<CampaignDiscount> discounts = c.getCampaign_discount_list();

                    for(CampaignDiscount cd : discounts) {

                        Product p = product_repo.findById(cd.getProduct_id()).get();

                        double amount = (p.getCurrent_price() * cd.getDiscount()) / 100;
                        Running_Campaign rc = new Running_Campaign(c.getCampaign_id(), c.getE_date(), cd.getProduct_id(), cd.getDiscount(), amount);
                        running_campaign_repo.save(rc);

                        Discount d = discount_repo.findById(cd.getProduct_id()).get();
                        d.addDiscount(cd.getDiscount());
                        discount_repo.save(d);

                        p.addDiscount(cd.getDiscount());
                        product_repo.save(p);

                        p = product_repo.findById(cd.getProduct_id()).get();

                        History h = new History(today, cd.getProduct_id(), p.getCurrent_price());
                        history_repo.save(h);
                    }

                    c.setFlag(1);
            }

            //remove the campaign
            for(Campaign c : remove) {
                    List<Running_Campaign> RCList = running_campaign_repo.findByCampaign_id(c.getCampaign_id());
                    for(Running_Campaign rc : RCList) {

                        Product p = product_repo.findById(rc.getProduct_id()).get();

                        Discount d = discount_repo.findById(rc.getProduct_id()).get();
                        d.removeDiscount(rc.getDiscount());
                        discount_repo.save(d);

                        p.removeDiscount(rc.getDiscount_amount());
                        product_repo.save(p);

                        p = product_repo.findById(rc.getProduct_id()).get();

                        History h = new History(today, rc.getProduct_id(), p.getCurrent_price());
                        history_repo.save(h);
                    }
                    c.setFlag(2);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Campaign> getPastCampaign() {
        return campaign_repo.findByS_dateBefore(LocalDate.now());
    }

    public List<Campaign> getUpcomingCampaign() {
        return campaign_repo.findByS_dateAfter(LocalDate.now());
    }

    public List<Campaign> getRunningCampaign() {
        return campaign_repo.findRunningCampaign(LocalDate.now());
    }

    public void deleteAll() {
        campaign_repo.deleteAll();
    }

    public void deleteById(int id) {
        campaign_repo.deleteById(id);
    }
}
