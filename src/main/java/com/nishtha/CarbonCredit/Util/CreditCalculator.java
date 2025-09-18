package com.nishtha.CarbonCredit.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CreditCalculator {

    private final double factor;

    public CreditCalculator(@Value("${app.credit.factorPerAcre:1.0}") double factor) {
        this.factor = factor;
    }

    public double estimate(double area) {
        return area * factor;
    }
}
