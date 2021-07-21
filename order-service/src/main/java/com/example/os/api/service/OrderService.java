package com.example.os.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.os.api.common.Payment;
import com.example.os.api.common.TransactionRequest;
import com.example.os.api.common.TransactionResponse;
import com.example.os.api.entity.Order;
import com.example.os.api.repository.OrderRepository;

@Service
public class OrderService {
	
	 @Autowired
	 private OrderRepository repository;
	 @Autowired
	 private RestTemplate template;
	 
	public TransactionResponse saveOrder(TransactionRequest request) {
		 String response = "";
			Order order = request.getOrder();
	        Payment payment = request.getPayment();
	        payment.setOrderId(order.getId());
	        payment.setAmount(order.getPrice());
	        Payment paymentResponse=  template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
	        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "there is a failure in payment api , order added to cart";
		 repository.save(order);
		 return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
	}

}
