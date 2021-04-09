package com.delivery.controller;

import com.delivery.controller.dto.BillRequest;
import com.delivery.controller.dto.BillResponse;
import com.delivery.controller.dto.ErrorResponse;
import com.delivery.model.Bill;
import com.delivery.service.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/bills")
public class BillsController {

    private final BillService service;

    public BillsController(BillService service) {
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Bill createBill(@RequestBody BillRequest billRequest) {
        return service.addBill(billRequest);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<BillResponse> getAllBills() {
        return service.getAll();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(){
        return new ErrorResponse("Could not perform the operation.");
    }
}
