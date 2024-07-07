package org.example.springex.service;

import java.util.ArrayList;
import java.util.List;

import org.example.springex.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private List<Product> products = new ArrayList<>();

	public void addProduct(Product product) {
		products.add(product);
	}

	public List<Product> findAllProduct() {
		return products;
	}
}
