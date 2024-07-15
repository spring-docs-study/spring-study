package org.example.springex.service;

import org.example.springex.exception.NotEnoughMoneyException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	public PaymentDetails processPayment() {
		throw new NotEnoughMoneyException();
	}

}
