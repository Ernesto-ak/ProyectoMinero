package com.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.model.Pedidos;

public interface PedidoRepository extends JpaRepository<Pedidos, Integer> {
	List<Pedidos> findByUsuarioIdContainingIgnoreCase(Integer id);

	Optional<Pedidos> findById(Integer id);

	@Query("SELECT p FROM Pedidos p WHERE usuario = :id")
	List<Pedidos> pedidoPorUsuario(@Param("id") String pedidos);
}
