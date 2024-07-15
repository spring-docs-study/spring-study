package org.example.springex.controller;

import java.util.logging.Logger;

import org.example.springex.Errors.ErrorDetails;
import org.example.springex.service.PaymentDetails;
import org.example.springex.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
	private Logger log = Logger.getLogger(PaymentController.class.getName());

	private final PaymentService paymentService;

	public PaymentController (PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping("/payment")
	public ResponseEntity<PaymentDetails> makePayment(@RequestBody PaymentDetails paymentDetails) {
		log.info("Received payment : " +  paymentDetails.getAmount());

		return ResponseEntity
			.status(HttpStatus.ACCEPTED)
			.body(paymentDetails);

	}

}
