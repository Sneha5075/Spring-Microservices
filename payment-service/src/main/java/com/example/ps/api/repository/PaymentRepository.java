package com.example.ps.api.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ps.api.entity.Payment;

public interface PaymentRepository extends MongoRepository<Payment, Integer>  {

	Payment findByOrderId(int orderId);

}
