package org.example.springex.proxy;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class PaymentProxy {

/*	public PaymentProxy (WebClient webClient) {
		this.webClient = webClient;
	}

	private final WebClient webClient;

	@Value("${name.service.url}")
	private String paymentServiceUrl;

	public Mono<Payment> createPayment(String requestId, Payment payment) {
		return webClient.post()
			.uri(paymentServiceUrl + "/payment")
			.header("requestId : ", requestId)
			.body(Mono.just(payment), Payment.class)
			.retrieve()
			.bodyToMono(Payment.class);
	}*/


}
