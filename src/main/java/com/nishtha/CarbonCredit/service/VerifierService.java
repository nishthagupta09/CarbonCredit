package com.nishtha.CarbonCredit.service;


import com.nishtha.CarbonCredit.DTOs.DecisionRequest;
import com.nishtha.CarbonCredit.entity.Information;
import com.nishtha.CarbonCredit.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VerifierService {
    private final InfoRepository infoRepository;

    public List<Information> getPendingProjects(String state) {
        return infoRepository.findByStateAndStatus(state, "COMPLETED_PENDING_VERIFIER");
    }

    public Information takeDecision(DecisionRequest req) {
        Information project = infoRepository.findById(req.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        if (!"COMPLETED_PENDING_VERIFIER".equals(project.getStatus())) {
            throw new IllegalStateException("Project not pending verification");
        }
        if ("APPROVED".equals(req.getDecision())) {
            project.setFinalCredits(project.getEstimatedCredits());
        }
        project.setStatus(req.getDecision());
        return infoRepository.save(project);
    }
}

