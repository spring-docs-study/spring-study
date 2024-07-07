package org.example.springex.controller;

import java.util.List;

import org.example.springex.domain.Product;
import org.example.springex.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController (ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/api/v1/products")
	public String addProducst1(Product product, Model model) {
		productService.addProduct(product);
		model.addAttribute("product", product);
		return "products.html";
	}

	@GetMapping("/api/products")
	public String viewProducts(Model model) {

		List<Product> allProduct = productService.findAllProduct();
		model.addAttribute("products", allProduct);
		return "products.html";
	}

	@PostMapping("/api/add")
	public String addProduct(Model model) {
		Product product = new Product();
		product.setName("진현규");
		product.setPrice(1000000000.10);
		productService.addProduct(product);

		return "redirect:/api/products";
	}
}
