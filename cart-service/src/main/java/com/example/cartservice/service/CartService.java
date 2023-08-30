package com.example.cartservice.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.cartservice.dto.ProductResponse;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartProduct;
import com.example.cartservice.repository.CartProductRepository;
import com.example.cartservice.repository.CartRepository;

@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartProductRepository cartProductRepository;

	// Para que funcione se crea en la configuracion como @Bean
	@Autowired
	private RestTemplate restTemplate;

	public void addProduct(Long id, Long idProduct, Integer quantity) {
		// Hemos llamado al microservicio
		// Realmente hay 2 maneras de comunicar microservicios. Una de ellas es con
		// REStTemplate y la otra con cliente feign
		// El segundo parametro es lo que devuelve
		final ProductResponse product = restTemplate.getForObject("http://catalog/product" + idProduct,
				ProductResponse.class);
		// Tambien podria haber convertido el productResponse a Product y pasarlo ahora
		// como argumento
		// Llamo al sg metodo... pero ahora tengo 2 datos nuevos: precio y nombre
		this.addProduct(id, idProduct, product.getPrice(), product.getName(), quantity);
	}

	public void addProduct(Long id, Long idProduct, BigDecimal price, String name, Integer quantity) {
		final Cart cart = cartRepository.findById(id).orElseThrow();

		for (int i = 0; i < quantity; i++) {
			CartProduct cp = new CartProduct();
			cp.setCart(cart);
			cp.setProduct_id(idProduct);
			cp.setPrice(price);
			cp.setName(name);
			cartProductRepository.save(cp);
		}
	}
}
