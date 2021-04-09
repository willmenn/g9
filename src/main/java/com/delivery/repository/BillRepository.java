package com.delivery.repository;

import com.delivery.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BillRepository extends CrudRepository<Bill, Integer> {

}
