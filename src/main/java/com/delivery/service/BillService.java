package com.delivery.service;

import com.delivery.controller.dto.BillRequest;
import com.delivery.controller.dto.BillResponse;
import com.delivery.model.Bill;
import com.delivery.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.delivery.controller.dto.BillResponse.of;
import static java.util.stream.StreamSupport.stream;

@Service
public class BillService {

    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public Bill addBill(BillRequest billRequest) {
        Double fees = FeesRule.getRule(billRequest.getPayDate(), billRequest.getEndDate())
                              .apply(billRequest.getPayDate(), billRequest.getEndDate());

        return repository.save(Bill.of(billRequest, fees));
    }

    public List<BillResponse> getAll() {
        return stream(repository.findAll().spliterator(), false)
                .map(bill -> of(bill, updatedValue(bill)))
                .collect(Collectors.toList());
    }

    private Double updatedValue(Bill bill) {
        return bill.getOriginalValue() + (bill.getOriginalValue() * (bill.getFees() / 100));
    }
}
