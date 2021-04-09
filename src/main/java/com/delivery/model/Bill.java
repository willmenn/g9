package com.delivery.model;

import com.delivery.controller.dto.BillRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static java.lang.Math.max;
import static java.time.Period.between;

@Entity
public class Bill {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Double originalValue;
    private LocalDate endDate;
    private LocalDate payDate;
    private Double fees;
    private int delayedDays;

    public Bill() {
    }

    public Bill(String name, Double originalValue, LocalDate endDate, LocalDate payDate, Double fees, int delayedDays) {
        this.name = name;
        this.originalValue = originalValue;
        this.endDate = endDate;
        this.payDate = payDate;
        this.fees = fees;
        this.delayedDays = delayedDays;
    }

    public static Bill of(BillRequest request, Double fees){
        return new Bill(request.getName(),
                        request.getOriginalValue(),
                        request.getEndDate(),
                        request.getPayDate(),
                        fees,
                        max(0,between(request.getPayDate(), request.getEndDate()).getDays()));
    }

    public String getName() {
        return name;
    }

    public Double getOriginalValue() {
        return originalValue;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public Double getFees() {
        return fees;
    }

    public int getDelayedDays() {
        return delayedDays;
    }
}
