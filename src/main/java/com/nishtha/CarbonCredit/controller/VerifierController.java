package com.nishtha.CarbonCredit.controller;


import com.nishtha.CarbonCredit.DTOs.DecisionRequest;
import com.nishtha.CarbonCredit.entity.Information;
import com.nishtha.CarbonCredit.service.VerifierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verifier")
@RequiredArgsConstructor
public class VerifierController {

    private final VerifierService verifierService;

    //@PreAuthorize("hasRole('VERIFIER')")
    @GetMapping("/projects")
    public List<Information> pending(@RequestParam String state) {
        return verifierService.getPendingProjects(state);
    }

    //@PreAuthorize("hasRole('VERIFIER')")
    @PostMapping("/decision")
    public Information decision(@RequestBody @Valid DecisionRequest req) {
        return verifierService.takeDecision(req);
    }
}

