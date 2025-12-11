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

import com.restaurant.model.Producto;
import com.restaurant.service.ProductoService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping
	public ResponseEntity<List<Producto>> obtenerTodos() {
		List<Producto> productos = productoService.obtenerTodos();
		return ResponseEntity.ok(productos);
	}

	@GetMapping("/restaurant/{idRestaurant}")
	public ResponseEntity<List<Producto>> obtenerPorRestaurant(@PathVariable Integer idRestaurant) {
		List<Producto> productos = productoService.buscarPorRestaurant(idRestaurant);
		return ResponseEntity.ok(productos);
	}

	@GetMapping("/categorias/{idCategoria}")
	public ResponseEntity<List<Producto>> obtenerPorCategoria(@PathVariable Integer idCategoria) {
		List<Producto> productos = productoService.buscarPorCategoria(idCategoria);
		return ResponseEntity.ok(productos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {
		Optional<Producto> producto = productoService.obtenerPorId(id);
		return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
		Producto productoNuevo = productoService.guardar(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto producto) {
		Producto actualizado = productoService.actualizar(id, producto);
		if (actualizado != null) {
			return ResponseEntity.ok(actualizado); 
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		productoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
