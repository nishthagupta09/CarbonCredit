package com.nishtha.CarbonCredit.repository;

import com.nishtha.CarbonCredit.entity.PayoutRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayoutRepository extends MongoRepository<PayoutRequest, String> {
    List<PayoutRequest> findByFarmerId(String farmerId);
}
