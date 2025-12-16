package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.model.DetallePedidos;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidos, Integer>{

}
