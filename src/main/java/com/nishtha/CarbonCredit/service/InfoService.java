package com.nishtha.CarbonCredit.service;

import com.nishtha.CarbonCredit.DTOs.InfoRequest;
import com.nishtha.CarbonCredit.DTOs.PhotoUploadRequest;
import com.nishtha.CarbonCredit.Util.CreditCalculator;
import com.nishtha.CarbonCredit.entity.Information;
import com.nishtha.CarbonCredit.entity.Photo;
import com.nishtha.CarbonCredit.entity.User;
import com.nishtha.CarbonCredit.entity.VerificationResult;
import com.nishtha.CarbonCredit.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoService {

    private final InfoRepository infoRepository;
    private final CreditCalculator creditCalculator;
    private final AuthService authService;

    public Information createProject(InfoRequest req, Authentication auth) {
        String email = (String) auth.getPrincipal();
        User farmer = authService.findByEmail(email);

        Information project = Information.builder()
                .farmerId(farmer.getId())
                .farmerEmail(farmer.getEmail())
                .crop(req.getCrop())
                .method(req.getMethod())
                .area(req.getArea())
                .state(req.getState())
                .status("ACTIVE")
                .build();

        return infoRepository.save(project);
    }

    public Information listOwnershipCheck(String projectId, Authentication auth) {
        Information p = infoRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        String email = (String) auth.getPrincipal();
        if (!email.equalsIgnoreCase(p.getFarmerEmail())) {
            throw new SecurityException("Not your project");
        }
        return p;
    }

    public List<Information> listMyProjects(Authentication auth) {
        String email = (String) auth.getPrincipal();
        return infoRepository.findByFarmerEmail(email.toLowerCase());
    }

    public Information uploadPhoto(String projectId, PhotoUploadRequest req, Authentication auth) {
        Information project = listOwnershipCheck(projectId, auth);

        // Simulate AI/SAT check
        VerificationResult ai = VerificationResult.builder()
                .type("AI").passed(Math.random() > 0.3)
                .confidence(Math.floor(Math.random() * 41) + 60) // 60..100
                .build();
        VerificationResult sat = VerificationResult.builder()
                .type("SAT").passed(Math.random() > 0.3)
                .confidence(Math.floor(Math.random() * 41) + 60)
                .build();

        Photo photo = Photo.builder()
                .url(req.getUrl())
                .timestamp(LocalDateTime.now())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .checks(List.of(ai, sat))
                .build();

        project.getPhotos().add(photo);
        return infoRepository.save(project);
    }

    public Information completeProject(String id, Authentication auth) {
        Information project = listOwnershipCheck(id, auth);
        double credits = creditCalculator.estimate(project.getArea());
        project.setEstimatedCredits(credits);
        project.setStatus("COMPLETED_PENDING_VERIFIER");
        return infoRepository.save(project);
    }
}
