package com.delivery.service;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.time.Period.between;

public enum FeesRule {
    NO_FEE(isNoFeeRequired(), (payDate, endDate) -> 0.),
    UNTIL_3_DAYS(isBefore3Days(), calculateFee(0.1, 2)),
    ABOVE_3_DAYS_LESS_THAN_5(isAbove3DaysAndBelow5Days(), calculateFee(0.2, 3)),
    ABOVE_5_DAYS(isAbove5Days(), calculateFee(0.3, 5));

    private static BiPredicate<LocalDate, LocalDate> isNoFeeRequired() {
        return (payDate, endDate) ->  endDate.isBefore(payDate) || endDate.equals(payDate);
    }

    private final static EnumSet<FeesRule> all = EnumSet.allOf(FeesRule.class);
    private BiPredicate<LocalDate, LocalDate> containsWithinThisRule;
    private BiFunction<LocalDate, LocalDate, Double> feeRule;
    private Function<LocalDate, Double> feeRule1;

    FeesRule(BiPredicate<LocalDate, LocalDate> containsWithinThisRule,
             BiFunction<LocalDate, LocalDate, Double> feeRule) {
        this.containsWithinThisRule = containsWithinThisRule;
        this.feeRule = feeRule;
    }

    public static FeesRule getRule(LocalDate payDate, LocalDate endDate) {
        return all.stream()
                  .filter(rule -> rule.containsWithinThisRule.test(payDate, endDate))
                  .findFirst()
                  .orElseThrow(NullPointerException::new);
    }

    public Double apply(LocalDate payDate, LocalDate endDate) {
        return feeRule.apply(payDate, endDate);
    }

    private static BiFunction<LocalDate, LocalDate, Double> calculateFee(double perDay, int fixedRate) {
        return (payDate, endDate) -> between(payDate, endDate).getDays() * perDay + fixedRate;
    }

    private static BiPredicate<LocalDate, LocalDate> isAbove5Days() {
        return (payDate, endDate) -> isPayDateAfterEndDate(payDate, endDate)
                                     && between(payDate, endDate).getDays() >= 5;
    }

    private static BiPredicate<LocalDate, LocalDate> isAbove3DaysAndBelow5Days() {
        return (payDate, endDate) -> isPayDateAfterEndDate(payDate, endDate)
                                     && between(payDate, endDate).getDays() < 5
                                     && between(payDate, endDate).getDays() >= 3;
    }

    private static BiPredicate<LocalDate, LocalDate> isBefore3Days() {
        return (payDate, endDate) -> isPayDateAfterEndDate(payDate, endDate)
                                     && between(payDate, endDate).getDays() < 3;
    }

    private static boolean isPayDateAfterEndDate(LocalDate payDate, LocalDate endDate) {
        return endDate.isAfter(payDate);
    }
}
