package com.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.model.Pedidos;
import com.restaurant.model.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Integer> {

	List<Pedidos> findByUsuario(String usuario);

	Optional<Pedidos> findById(Integer id);

	@Query("SELECT p FROM Pedidos p WHERE p.usuario.id = :id")
	List<Pedidos> pedidoPorUsuario(@Param("id") String pedidos);

	Pedidos findByUsuarioAndEstado(Usuario usuario, String pendiente);
}