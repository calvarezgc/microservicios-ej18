package com.example.catalogservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catalogservice.dto.ProductAdapter;
import com.example.catalogservice.dto.ProductDTO;
import com.example.catalogservice.entity.Product;
import com.example.catalogservice.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired 
    private ProductRepository productRepository;
    
    @Autowired 
    private ProductAdapter productAdapter;

    @GetMapping()
    public List<ProductDTO> list() {
        final List<Product> all = productRepository.findAll();
        //La adaptacion (paso a DTO) se hace en esta capa de control; no en Service o repo
        return productAdapter.convertToDto(all);
    }

    @GetMapping("/{id}")
    public ProductDTO product(@PathVariable Long id) {
        log.info("--- product por id "+id);
        final Product product = productRepository.findById(id).orElseThrow();
      //La adaptacion (paso a DTO) se hace en esta capa de control; no en Service o repo
        return productAdapter.convertToDto(product);
    }
}
