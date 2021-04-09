package com.delivery.controller.dto;

import java.time.LocalDate;

public class BillRequest {

    private String name;
    private Double originalValue;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate payDate;

    public BillRequest() {
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
}
