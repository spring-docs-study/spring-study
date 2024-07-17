package org.example.springex.controller;

import java.util.UUID;
import java.util.logging.Logger;

import org.example.springex.domain.Payment;
import org.example.springex.proxy.PaymentProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class PaymentController {
	private Logger log = Logger.getLogger(PaymentController.class.getName());

	private final PaymentProxy paymentProxy;

	public PaymentController (PaymentProxy proxy) {
		this.paymentProxy = proxy;
	}


	@PostMapping("/payment")
	public Mono<Payment> createPayment(@RequestBody Payment payment) {
		String requestId = UUID.randomUUID().toString();
		return paymentProxy.createPayment(requestId,payment);
	}
}
