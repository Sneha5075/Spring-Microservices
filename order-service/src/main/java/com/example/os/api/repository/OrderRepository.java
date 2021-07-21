package com.example.os.api.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.os.api.entity.Order;

public interface OrderRepository extends MongoRepository<Order, Integer>  {

}
