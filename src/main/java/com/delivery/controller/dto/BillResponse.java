package com.delivery.controller.dto;

import com.delivery.model.Bill;

import java.time.LocalDate;

public class BillResponse {

    private String name;
    private Double originalValue;
    private Double updatedValue;
    private Integer delayedDays;
    private LocalDate payDate;

    public BillResponse(String name, Double originalValue, Double updatedValue, Integer delayedDays,
                        LocalDate payDate) {
        this.name = name;
        this.originalValue = originalValue;
        this.updatedValue = updatedValue;
        this.delayedDays = delayedDays;
        this.payDate = payDate;
    }

    public static BillResponse of(Bill bill, Double updatedValue){
        return new BillResponse(bill.getName(), bill.getOriginalValue(),
                                updatedValue,
                                bill.getDelayedDays(),
                                bill.getPayDate());
    }

    public String getName() {
        return name;
    }

    public Double getOriginalValue() {
        return originalValue;
    }

    public Double getUpdatedValue() {
        return updatedValue;
    }

    public Integer getDelayedDays() {
        return delayedDays;
    }

    public LocalDate getPayDate() {
        return payDate;
    }
}
