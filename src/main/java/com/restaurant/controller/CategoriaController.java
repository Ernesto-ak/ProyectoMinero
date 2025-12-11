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

import com.restaurant.model.Categoria;
import com.restaurant.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> obtenerTodos() {
		List<Categoria> categorias = categoriaService.obtenerTodos();
		return ResponseEntity.ok(categorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obtenerPorId(@PathVariable Integer id) {
		Optional<Categoria> categoria = categoriaService.obtenerPorId(id);
		return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
		Categoria categoriaNuevo = categoriaService.guardar(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaNuevo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
		Categoria actualizado = categoriaService.actualizar(id, categoria);
		if (actualizado != null) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		categoriaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
