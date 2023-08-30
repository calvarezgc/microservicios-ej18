package com.example.catalogservice.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.catalogservice.entity.Product;

/*
 * La utilizo para convertir de entity a DTO
 */
@Component
public class ProductAdapter {

	public ProductDTO convertToDto(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setName(product.getName());
		return productDTO;
	}

	public List<ProductDTO> convertToDto(List<Product> products) {
		return products.stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
}
