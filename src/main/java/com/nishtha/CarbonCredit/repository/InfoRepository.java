package com.nishtha.CarbonCredit.repository;

import com.nishtha.CarbonCredit.entity.Information;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InfoRepository extends MongoRepository<Information, String> {
    List<Information> findByFarmerId(String farmerId);
    List<Information> findByFarmerEmail(String email);
    List<Information> findByStateAndStatus(String state, String status);
}

