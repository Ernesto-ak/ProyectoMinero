package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.model.Producto;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre);
	List<Producto> findByRestaurantId(Integer restaurantId);
	List<Producto> findByCategoriaId(Integer categoriaId);
}
