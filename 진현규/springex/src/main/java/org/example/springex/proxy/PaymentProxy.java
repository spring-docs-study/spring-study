package org.example.springex.proxy;

import java.util.UUID;

import org.example.springex.domain.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class PaymentProxy {

	public PaymentProxy (WebClient webClient) {
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
	}


}
