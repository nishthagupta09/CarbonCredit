package com.nishtha.CarbonCredit.service;


import com.nishtha.CarbonCredit.DTOs.CashOutRequest;
import com.nishtha.CarbonCredit.entity.Information;
import com.nishtha.CarbonCredit.entity.PayoutRequest;
import com.nishtha.CarbonCredit.repository.InfoRepository;
import com.nishtha.CarbonCredit.repository.PayoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CashoutService {
    private final PayoutRepository payoutRepo;
    private final InfoRepository infoRepository;

    public PayoutRequest requestCashout(CashOutRequest req, Authentication auth) {
        Information p = infoRepository.findById(req.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        String email = (String) auth.getPrincipal();
        if (!email.equalsIgnoreCase(p.getFarmerEmail())) {
            throw new SecurityException("Not your project");
        }
        if (!"APPROVED".equals(p.getStatus())) {
            throw new IllegalStateException("Cashout allowed only after APPROVED");
        }

        PayoutRequest payout = PayoutRequest.builder()
                .farmerId(p.getFarmerId())
                .projectId(p.getId())
                .mode(req.getMode())
                .requestDate(LocalDateTime.now())
                .status("PENDING")
                .build();

        return payoutRepo.save(payout);
    }
}
