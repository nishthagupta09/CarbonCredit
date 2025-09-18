package com.nishtha.CarbonCredit.controller;


import com.nishtha.CarbonCredit.DTOs.CashOutRequest;
import com.nishtha.CarbonCredit.entity.PayoutRequest;
import com.nishtha.CarbonCredit.service.CashoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashout")
@RequiredArgsConstructor
public class CashoutController {

    private final CashoutService cashoutService;

    //@PreAuthorize("hasRole('FARMER')")
    @PostMapping
    public PayoutRequest cashout(@RequestBody @Valid CashOutRequest req, Authentication auth) {
        return cashoutService.requestCashout(req, auth);
    }
}
