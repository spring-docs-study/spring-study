package org.example.springex.controller;

import java.util.List;

import org.example.springex.domain.Purchase;
import org.example.springex.repository.PurchaseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class PurchaseController {
	private final PurchaseRepository purchaseRepository;

	public PurchaseController (PurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}

	@PostMapping("/purchase")
	public void storePurchase(@RequestBody Purchase purchase) {
		purchaseRepository.storePurchase(purchase);
	}

	@GetMapping("/getList")
	public List<Purchase> getList() {
		return purchaseRepository.purchases();
	}

}
