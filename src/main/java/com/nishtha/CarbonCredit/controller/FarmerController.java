package com.nishtha.CarbonCredit.controller;


import com.nishtha.CarbonCredit.DTOs.InfoRequest;
import com.nishtha.CarbonCredit.DTOs.PhotoUploadRequest;
import com.nishtha.CarbonCredit.entity.Information;
import com.nishtha.CarbonCredit.service.InfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class FarmerController {

    private final InfoService projectService;

    //@PreAuthorize("hasRole('FARMER')")
    @PostMapping
    public Information create(@RequestBody @Valid InfoRequest req, Authentication auth) {
        return projectService.createProject(req, auth);
    }

    //@PreAuthorize("hasRole('FARMER')")
    @GetMapping("/mine")
    public List<Information> myProjects(Authentication auth) {
        return projectService.listMyProjects(auth);
    }

    //@PreAuthorize("hasRole('FARMER')")
    @PostMapping("/{id}/photos")
    public Information uploadPhoto(@PathVariable String id,
                               @RequestBody @Valid PhotoUploadRequest req,
                               Authentication auth) {
        return projectService.uploadPhoto(id, req, auth);
    }

    //@PreAuthorize("hasRole('FARMER')")
    @PostMapping("/{id}/complete")
    public Information complete(@PathVariable String id, Authentication auth) {
        return projectService.completeProject(id, auth);
    }
}

