package com.delivery.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FeesRuleTest {

    @Test
    void shouldBeAbleToApplyNoFees() {
        FeesRule rule = FeesRule.getRule(LocalDate.now(), LocalDate.now());
        Double fee = rule.apply(LocalDate.now(), LocalDate.now());
        assertEquals(0, fee);
    }

    @Test
    void shouldBeAbleToApplyFeesGivenIsLessThan3DaysLate() {
        FeesRule rule = FeesRule.getRule(LocalDate.now(), LocalDate.now().plusDays(1));
        Double fee = rule.apply(LocalDate.now(), LocalDate.now().plusDays(1));
        assertEquals(2.1, fee);
    }

    @Test
    void shouldBeAbleToApplyFeesGivenIsBiggerThan3DaysAndLessThan5Days() {
        FeesRule rule = FeesRule.getRule(LocalDate.now(), LocalDate.now().plusDays(3));
        Double fee = rule.apply(LocalDate.now(), LocalDate.now().plusDays(3));
        assertEquals(3.6, fee);
    }

    @Test
    void shouldBeAbleToApplyFeesGivenIsBiggerThan5Days() {
        FeesRule rule = FeesRule.getRule(LocalDate.now(), LocalDate.now().plusDays(5));
        Double fee = rule.apply(LocalDate.now(), LocalDate.now().plusDays(5));
        assertEquals(6.5, fee);
    }
}