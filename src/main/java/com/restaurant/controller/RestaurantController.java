package com.restaurant.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.model.Restaurant;
import com.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "*")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public ResponseEntity<List<Restaurant>> obtenerTodos() {
		List<Restaurant> restaurantes = restaurantService.obtenerTodos();
		return ResponseEntity.ok(restaurantes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> obtenerPorId(@PathVariable Integer id) {
		Optional<Restaurant> restaurant = restaurantService.obtenerPorId(id);
		return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Restaurant> crear(@RequestBody Restaurant restaurant) {
		Restaurant restaurantNuevo = restaurantService.guardar(restaurant);
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantNuevo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> actualizar(@PathVariable Integer id, @RequestBody Restaurant restaurant) {
		Restaurant actualizado = restaurantService.actualizar(id, restaurant);
		if (actualizado != null) {
			return ResponseEntity.ok(restaurant);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		restaurantService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
