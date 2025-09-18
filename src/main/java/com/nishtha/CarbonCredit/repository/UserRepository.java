package com.nishtha.CarbonCredit.repository;

import com.nishtha.CarbonCredit.entity.Information;
import com.nishtha.CarbonCredit.entity.PayoutRequest;
import com.nishtha.CarbonCredit.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}



